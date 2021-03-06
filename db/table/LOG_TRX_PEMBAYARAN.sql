
  CREATE TABLE "PBBDUMMY"."LOG_TRX_PEMBAYARAN"
   (	"NOP" VARCHAR2(18 BYTE) NOT NULL ENABLE,
	"THN" VARCHAR2(4 BYTE) NOT NULL ENABLE,
	"NTPD" VARCHAR2(30 BYTE) NOT NULL ENABLE,
	"POKOK" NUMBER,
	"NAMA_WP" VARCHAR2(50 BYTE),
	"ALAMAT_OP" VARCHAR2(150 BYTE),
	"MATA_ANGGARAN" VARCHAR2(15 BYTE),
	"MA_SANKSI" VARCHAR2(20 BYTE),
	"DENDA" NUMBER,
	"PEMBAYARAN_KE" NUMBER(2,0),
	"IP_CLIENT" VARCHAR2(30 BYTE),
	 CONSTRAINT "LOG_TRX_PEMBAYARAN_PK" PRIMARY KEY ("NOP", "THN", "NTPD")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DAT"  ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DAT" ;
