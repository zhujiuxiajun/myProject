SELECT * FROM device a where a.name = '眉山市仁寿县观寺镇鳌陵乡宝丰四组6号箱OBD01-00-01';
SELECT t.*,t.rowid FROM odd t WHERE    t.id = '603178671';--odd表中改设备下没有olt设备

SELECT t.*,t.rowid FROM odd t WHERE    t.olt_id = '603178668';--若下面语句查出来为空，则删除odd表中数据的oltid字段

SELECT * FROM port a WHERE /*  a.id = '123843112';*/ a.device_id = '603178668';
SELECT * FROM device a WHERE a.id = '603178668';
