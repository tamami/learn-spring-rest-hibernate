create or replace procedure reversal_pembayaran(c_data out sys_refcursor,
  v_nop in varchar, v_thn in varchar, v_ntpd in varchar, v_ip_client in varchar)
is
  v_adaData numeric;
  v_kdPropinsi sppt.kd_propinsi%type;
  v_kdDati2 sppt.kd_dati2%type;
  v_kdKecamatan sppt.kd_kecamatan%type;
  v_kdKelurahan sppt.kd_kelurahan%type;
  v_kdBlok sppt.kd_blok%type;
  v_noUrut sppt.no_urut%type;
  v_kdJnsOp sppt.kd_jns_op%type;
  v_pembayaranKe log_trx_pembayaran.pembayaran_ke%type;

begin
  -- error code in c_data
  -- 01 : NO DATA FOUND
  -- 02 : DATA GANDA

  -- set parameter nop
  v_kdPropinsi := substr(v_nop,1,2);
  v_kdDati2 := substr(v_nop,3,2);
  v_kdKecamatan := substr(v_nop,5,3);
  v_kdKelurahan := substr(v_nop,8,3);
  v_kdBlok := substr(v_nop,11,3);
  v_noUrut := substr(v_nop,14,4);
  v_kdJnsOp := substr(v_nop,18,1);

  -- verifikasi data, ada atau ga
  select count(log_trx.nop)
  into v_adaData
  from log_trx_pembayaran log_trx
  where log_trx.ntpd = v_ntpd
    and log_trx.nop = v_nop
    and log_trx.thn = v_thn;

  if(v_adaData = 0) then
    open c_data for
      select '01' as kode_error from dual;
    goto exit_karena_error;
  elsif(v_adaData > 1) then
    open c_data for
      select '02' as kode_error from dual;
    goto exit_karena_error;
  end if;

  -- ambil data dari log_trx_pembayaran
  select pembayaran_ke
  into v_pembayaranKe
  from log_trx_pembayaran log_trx
  where log_trx.nop = v_nop
    and log_trx.thn = v_thn
    and log_trx.ntpd = v_ntpd;

  -- hapus data di pembayaran_sppt
  delete from pembayaran_sppt
  where thn_pajak_sppt = v_thn
    and kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan
    and kd_kelurahan = v_kdKelurahan
    and kd_blok = v_kdBlok
    and no_urut = v_noUrut
    and kd_jns_op = v_kdJnsOp
    and pembayaran_sppt_ke = v_pembayaranKe;
  commit;

  -- ubah isi sppt.status_pembayaran_sppt = '0'
  update sppt
  set status_pembayaran_sppt = '0'
  where thn_pajak_sppt = v_thn
    and kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan
    and kd_kelurahan = v_kdKelurahan
    and kd_blok = v_kdBlok
    and no_urut = v_noUrut
    and kd_jns_op = v_kdJnsOp;
  commit;

  -- catat di log_reversal
  insert into log_reversal log_r
    (log_r.nop, log_r.thn, log_r.ntpd, log_r.ip_client)
  values (v_nop, v_thn, v_ntpd, v_ip_client);
  commit;

  open c_data for
    select v_nop as NOP, v_thn as THN, v_ntpd as NTPD from dual;

  <<exit_karena_error>>
  return;
end;
