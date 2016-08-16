create or replace procedure proses_pembayaran(c_proses_pembayaran out sys_refcursor,
    nop in varchar, thn in varchar, tgl_bayar in date, ip_client in varchar)
is
  v_kdPropinsi sppt.kd_propinsi%type;
  v_kdDati2 sppt.kd_dati2%type;
  v_kdKecamatan sppt.kd_kecamatan%type;
  v_kdKelurahan sppt.kd_kelurahan%type;
  v_kdBlok sppt.kd_blok%type;
  v_noUrut sppt.no_urut%type;
  v_kdJnsOp sppt.kd_jns_op%type;
  v_pokokTemp sppt.pbb_yg_harus_dibayar_sppt%type;
  v_dendaTemp numeric;
  v_tglJatuhTempo sppt.tgl_jatuh_tempo_sppt%type;
  v_kdKanwilBank sppt.kd_kanwil_bank%type;
  v_kdKppbbBank sppt.kd_kppbb_bank%type;
  v_kdBankTunggal sppt.kd_bank_tunggal%type;
  v_kdBankPersepsi sppt.kd_bank_persepsi%type;
  v_kdTp sppt.kd_tp%type;
  v_statusPembayaranSppt sppt.status_pembayaran_sppt%type;
  v_pembayaranKe numeric;
  v_tglRekam date;
  v_jamRekam timestamp;

  v_ntpd varchar2(50);
  v_namaWp sppt.nm_wp_sppt%type;
  v_panjangNamaWp number;
  v_znt dat_op_bumi.kd_znt%type;
  v_nilaiSistemBumi dat_op_bumi.nilai_sistem_bumi%type;
  v_mataAnggaran varchar2(20); -- '4.1.1.11.01'; -- ref_jns_sektor.kd_sektor = 02
                             -- '4.1.1.11.02'; -- ref_jns_sektor.kd_sektor = 01
  v_mataAnggaranSanksi varchar2(20);
  v_kdSektor ref_kelurahan.kd_sektor%type;
  v_nmKelurahan ref_kelurahan.nm_kelurahan%type;
  v_nmKecamatan ref_kecamatan.nm_kecamatan%type;

begin
  -- error code in c_proses_pembayaran
  -- 01 : NO DATA FOUND
  -- 02 : POKOK PEMBAYARAN BERBEDA DENGAN TAGIHAN
  -- 03 : SPPT SUDAH TERBAYAR
  -- 04 : TAGIHAN SPPT DIBATALKAN

  -- set parameter nop
  v_kdPropinsi := substr(nop,1,2);
  v_kdDati2 := substr(nop,3,2);
  v_kdKecamatan := substr(nop,5,3);
  v_kdKelurahan := substr(nop,8,3);
  v_kdBlok := substr(nop,11,3);
  v_noUrut := substr(nop,14,4);
  v_kdJnsOp := substr(nop,18,1);

  -- query data dari tabel sppt
  select pbb_yg_harus_dibayar_sppt, tgl_jatuh_tempo_sppt, kd_kanwil_bank,
    kd_kppbb_bank, kd_bank_tunggal, kd_bank_persepsi, kd_tp, status_pembayaran_sppt,
    nm_wp_sppt
  into v_pokokTemp, v_tglJatuhTempo, v_kdKanwilBank,
    v_kdKppbbBank, v_kdBankTunggal, v_kdBankPersepsi, v_kdTp, v_statusPembayaranSppt,
    v_namaWp
  from sppt
  where thn_pajak_sppt = thn
    and kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan
    and kd_kelurahan = v_kdKelurahan
    and kd_blok = v_kdBlok
    and no_urut = v_noUrut
    and kd_jns_op = v_kdJnsOp;

  if(v_tglJatuhTempo = NULL) then
    open c_proses_pembayaran for
      select '01' as kode_error from dual; -- NO DATA FOUND
    goto exit_karena_error;
  end if;

  -- get data from dat_op_bumi
  select kd_znt, nilai_sistem_bumi
  into v_znt, v_nilaiSistemBumi
  from dat_op_bumi
  where kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan
    and kd_kelurahan = v_kdKelurahan
    and kd_blok = v_kdBlok
    and no_urut = v_noUrut
    and kd_jns_op = v_kdJnsOp;

  -- ambil kode sektor dan nama kelurahan
  select kd_sektor, nm_kelurahan
  into v_kdSektor, v_nmKelurahan
  from ref_kelurahan
  where kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan
    and kd_kelurahan = v_kdKelurahan;

  -- ambil nama kecamatan
  select nm_kecamatan
  into v_nmKecamatan
  from ref_kecamatan
  where kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan;

  -- cek pokok harus sama dengan pbb_yg_harus_dibayar_sppt
  --if(v_pokokTemp != pokok) then
  --  open c_proses_pembayaran for
  --    select '02' as kode_error from dual; -- pokok pembayaran yang dibayarkan berbeda dengan sismiop
  --  return;
  --end if;
  -- update: pembayaran langsung diambil dari db

  -- bila status pembayaran 1 atau v_pokokTemp = 0, tidak perlu dibayarkan, sudah terbayar
  if(v_statusPembayaranSppt = '1' or v_pokokTemp = 0) then
    open c_proses_pembayaran for
      select '03' as kode_error from dual;
    goto exit_karena_error;
  elsif(v_statusPembayaranSppt = 2) then
  -- bila status pembayaran 2, tidak perlu dibayarkan, tagihan dibatalkan
    open c_proses_pembayaran for
      select '04' as kode_error from dual;
    goto exit_karena_error;
  end if;

  -- cek denda
  if(thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,1)) then
    v_dendaTemp := ceil(1 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,2)) then
    v_dendaTemp := ceil(2 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,3)) then
    v_dendaTemp := ceil(3 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,4)) then
    v_dendaTemp := ceil(4 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,5)) then
    v_dendaTemp := ceil(5 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,6)) then
    v_dendaTemp := ceil(6 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,7)) then
    v_dendaTemp := ceil(7 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,8)) then
    v_dendaTemp := ceil(8 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,9)) then
    v_dendaTemp := ceil(9 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,10)) then
    v_dendaTemp := ceil(10 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,11)) then
    v_dendaTemp := ceil(11 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,12)) then
    v_dendaTemp := ceil(12 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,13)) then
    v_dendaTemp := ceil(13 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,14)) then
    v_dendaTemp := ceil(14 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar between (v_tglJatuhTempo+1) and add_months(v_tglJatuhTempo,15)) then
    v_dendaTemp := ceil(15 * 0.02 * v_pokokTemp);
  elsif (thn > '2013' and tgl_bayar >= add_months(v_tglJatuhTempo,15)) then
    v_dendaTemp := ceil(15 * 0.02 * v_pokokTemp);
  else v_dendaTemp := 0;
  end if;

  -- count pembayaran yang sudah masuk
  select count(kd_propinsi) into v_pembayaranKe
  from pembayaran_sppt
  where thn_pajak_sppt = thn
    and kd_propinsi = v_kdPropinsi
    and kd_dati2 = v_kdDati2
    and kd_kecamatan = v_kdKecamatan
    and kd_kelurahan = v_kdKelurahan
    and kd_blok = v_kdBlok
    and no_urut = v_noUrut
    and kd_jns_op = v_kdJnsOp;

  if(v_pembayaranKe = null) then
    v_pembayaranKe := 0;
  end if;

  v_tglRekam := sysdate;
  v_jamRekam := current_timestamp;
  v_pembayaranKe := v_pembayaranKe +1;

  -- simpan pembayaran di tabel pembayaran_sppt
  insert into pembayaran_sppt(kd_propinsi, kd_dati2, kd_kecamatan, kd_kelurahan,
    kd_blok, no_urut, kd_jns_op, thn_pajak_sppt, pembayaran_sppt_ke,
    kd_kanwil_bank, kd_kppbb_bank, kd_bank_tunggal, kd_bank_persepsi, kd_tp,
    denda_sppt, jml_sppt_yg_dibayar, tgl_pembayaran_sppt,
    tgl_rekam_byr_sppt, nip_rekam_byr_sppt)
  values(v_kdPropinsi, v_kdDati2, v_kdKecamatan, v_kdKelurahan,
    v_kdBlok, v_noUrut, v_kdJnsOp, thn, v_pembayaranKe,
    v_kdKanwilBank, v_kdKppbbBank, v_kdBankTunggal, v_kdBankPersepsi, v_kdTp,
    v_dendaTemp, (v_pokokTemp + v_dendaTemp), tgl_bayar,
    v_tglRekam, '060000000');
  commit;

  -- task: susun ntpd
  v_panjangNamaWp := length(v_namaWp);
  v_ntpd := to_char(tgl_bayar,'YYYY') ||
    substr(v_namaWp,-1,1) || substr(v_znt,1,1) || v_panjangNamaWp ||
    substr(to_char(v_nilaiSistemBumi),1,1) ||
    length(to_char(v_nilaiSistemBumi)) || to_char(v_jamRekam,'MI') ||
    substr(v_namaWp,1,1) || substr(v_znt,-1,1) || to_char(tgl_bayar,'DD') ||
    substr(to_char(v_nilaiSistemBumi),-1,1) ||
    substr(to_char(v_pokokTemp),2,1) || to_char(v_jamRekam, 'HH24') ||
    to_char(tgl_bayar,'MM');

  -- respond untuk pembayaran yang sukses
  if (v_kdsektor = '02') then
    v_mataAnggaran := '4.1.1.11.01';
  else
    v_mataAnggaran := '4.1.1.11.02';
  end if;

  v_mataAnggaranSanksi := v_mataAnggaran;

  -- SIMPAN TRANSAKSI DI LOG
  INSERT INTO LOG_TRX_PEMBAYARAN
    (NOP, THN, NTPD, MATA_ANGGARAN, POKOK, MA_SANKSI, DENDA, NAMA_WP, ALAMAT_OP, PEMBAYARAN_KE, ip_client)
  VALUES(nop, thn, v_ntpd, v_mataAnggaran, v_pokokTemp, v_mataAnggaranSanksi, v_dendaTemp, v_namaWp, v_nmKelurahan || ' - ' ||v_nmKecamatan,
    v_pembayaranKe, ip_Client);
  commit;

  open c_proses_pembayaran for
    select nop as nop, thn as thn, v_ntpd as ntpd, v_mataAnggaran as mata_anggaran_pokok,
      v_pokokTemp as pokok, v_mataAnggaranSanksi as mata_anggaran_sanksi, v_dendaTemp as sanksi,
      v_namaWp as nama_wp, v_nmKelurahan || ' - ' || v_nmKecamatan as alamat_op
    from dual;

  <<exit_karena_error>>
  return;
end;
