create or replace procedure sppt_terhutang(c_sppt out sys_refcursor, nop in varchar, thn in varchar) is
  v_kdPropinsi sppt.kd_propinsi%type;
  v_kdDati2 sppt.kd_dati2%type;
  v_kdKecamatan sppt.kd_kecamatan%type;
  v_kdKelurahan sppt.kd_kelurahan%type;
  v_kdBlok sppt.kd_blok%type;
  v_noUrut sppt.no_urut%type;
  v_kdJnsOp sppt.kd_jns_op%type;
begin
  v_kdPropinsi := substr(nop,1,2);
  v_kdDati2 := substr(nop,3,2);
  v_kdKecamatan := substr(nop,5,3);
  v_kdKelurahan := substr(nop,8,3);
  v_kdBlok := substr(nop,11,3);
  v_noUrut := substr(nop,14,4);
  v_kdJnsOp := substr(nop,18,1);

  open c_sppt for
    SELECT
    (sppt.kd_propinsi
    ||sppt.kd_dati2
    ||sppt.kd_kecamatan
    ||sppt.kd_kelurahan
    ||sppt.kd_blok
    ||sppt.no_urut
    ||sppt.kd_jns_op)              AS NOP,
    sppt.thn_pajak_sppt            AS THN,
    sppt.nm_wp_sppt                AS NAMA,
    kel.nm_kelurahan||' - '||kec.nm_kecamatan as "ALAMAT_OP",
    sppt.pbb_yg_harus_dibayar_sppt AS POKOK,
    case
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 1) then ceil(1 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 2) then ceil(2 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 3) then ceil(3 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 4) then ceil(4 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 5) then ceil(5 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 6) then ceil(6 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 7) then ceil(7 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 8) then ceil(8 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 9) then ceil(9 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 10) then ceil(10 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 11) then ceil(11 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 12) then ceil(12 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 13) then ceil(13 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 14) then ceil(14 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate between (tgl_jatuh_tempo_sppt+1) and add_months(tgl_jatuh_tempo_sppt, 15) then ceil(15 * 0.02 * pbb_yg_harus_dibayar_sppt)
      when thn > '2013' and sysdate > add_months(tgl_jatuh_tempo_sppt, 15) then ceil(15 * 0.02 * pbb_yg_harus_dibayar_sppt)
      else 0
    end as denda,
    sppt.status_pembayaran_sppt
  FROM sppt
  join ref_kecamatan kec on (
     kec.kd_propinsi = sppt.kd_propinsi
    and kec.kd_dati2  = sppt.kd_dati2
    and kec.kd_kecamatan = sppt.kd_kecamatan)
  join ref_kelurahan kel on (
     kel.kd_propinsi = sppt.kd_propinsi
    and kel.kd_dati2 = sppt.kd_dati2
    and kel.kd_kecamatan = sppt.kd_kecamatan
    and kel.kd_kelurahan = sppt.kd_kelurahan)
  where
    sppt.kd_propinsi = v_kdPropinsi and
    sppt.kd_dati2 = v_kdDati2 and
    sppt.kd_kecamatan = v_kdKecamatan and
    sppt.kd_kelurahan = v_kdKelurahan and
    sppt.kd_blok = v_kdBlok and
    sppt.no_urut = v_noUrut and
    sppt.kd_jns_op = v_kdJnsOp and
    sppt.thn_pajak_sppt = thn;
end;
