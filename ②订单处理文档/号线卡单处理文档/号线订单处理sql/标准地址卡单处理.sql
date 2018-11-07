SELECT a.*,a.rowid FROM device a where a.name = '四川省南充蓬安县文君路未来城1期A6-1PA3400/H-1';
SELECT a.*,a.rowid FROM odd a WHERE    a.id = '602523609';

--根据标准地址查询设备id（NETRESCODE字段），再根据设备id查odd表
select *
  from tenetrescover t
 where t.cydz = '四川省成都大邑晋原镇温泉路润驰广场对面建华小区‘15年乡镇专项第1批’6栋2单元1层01'

SELECT * FROM port a WHERE   a.id = '123843080';
SELECT * FROM device a WHERE a.id = '602518264';
