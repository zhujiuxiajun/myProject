成都青羊区倍特集团综合接入/MA5303
601276031 成都武侯北区川大汇聚机房C220   IMS局向
00-00-01-01
601168226

select t.*,t.rowid from device t where /*t.name = 'DXQ_内江东兴区二号路局房MA5680T'*/
 t.id = '603294540';
select t.*,t.rowid from dslam_device t where t.id = 603501528;
select t.*,t.rowid from onu_device t where t.id = 603502671;
select t.*,t.rowid from data_switch_device t where t.id =603501528;--楼道交换机
select t.*,t.rowid from port t where t.id = '126530108';--portid找到deviceid
 t.device_id = 601432701 and t.id_in_ems = '00-00-14-03';
--status 10：空闲 30：在用
select t.*,t.rowid from an_device_port t where t.id = '603502633';
select t.*,t.rowid from olt_device t where t.id ='603294540';

select * from ip_address t where t.id = '101183089';
select * from odd t where t.id = '603294540';--power=1,表示是自激活设备，power为空表示非自激活设备

select * from port_loid t where t.loid = 'LE603501520X0101006' --status = 30 占用

 
select * from tenetrescover t where t.cydz = '四川省广元利州区大石镇碧桂园12栋2单元5楼301#';
--通过订单号获取地址，根据地址查询设备id，再到odd表中查询

select * from exch t where t.name like '%IMS局向%' and t.area_id = '810022'; --局向编码


SELECT a.Svlan

  FROM vlan_divide_info A
 WHERE A.PORT_ID = 123205908 for update; --olt端口svlan信息表
