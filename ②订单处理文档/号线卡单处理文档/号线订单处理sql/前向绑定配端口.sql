�ɶ����������ؼ����ۺϽ���/MA5303
601276031 �ɶ����������ۻ���C220   IMS����
00-00-01-01
601168226

select t.*,t.rowid from device t where /*t.name = 'DXQ_�ڽ�����������·�ַ�MA5680T'*/
 t.id = '603294540';
select t.*,t.rowid from dslam_device t where t.id = 603501528;
select t.*,t.rowid from onu_device t where t.id = 603502671;
select t.*,t.rowid from data_switch_device t where t.id =603501528;--¥��������
select t.*,t.rowid from port t where t.id = '126530108';--portid�ҵ�deviceid
 t.device_id = 601432701 and t.id_in_ems = '00-00-14-03';
--status 10������ 30������
select t.*,t.rowid from an_device_port t where t.id = '603502633';
select t.*,t.rowid from olt_device t where t.id ='603294540';

select * from ip_address t where t.id = '101183089';
select * from odd t where t.id = '603294540';--power=1,��ʾ���Լ����豸��powerΪ�ձ�ʾ���Լ����豸

select * from port_loid t where t.loid = 'LE603501520X0101006' --status = 30 ռ��

 
select * from tenetrescover t where t.cydz = '�Ĵ�ʡ��Ԫ��������ʯ��̹�԰12��2��Ԫ5¥301#';
--ͨ�������Ż�ȡ��ַ�����ݵ�ַ��ѯ�豸id���ٵ�odd���в�ѯ

select * from exch t where t.name like '%IMS����%' and t.area_id = '810022'; --�������


SELECT a.Svlan

  FROM vlan_divide_info A
 WHERE A.PORT_ID = 123205908 for update; --olt�˿�svlan��Ϣ��
