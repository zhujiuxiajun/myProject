----1.��ǰ���ڲ��Ǵ��컷��
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%���÷���ͨ�����쳣:��ǰ���ڲ��Ǵ��컷�ڣ�%';

----2.BSS�Ķ�������ΪIOM�Ķ���
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%com.cattsoft.im.component.domain.TxRmsServiceDOM%';
--����
/*update res_interface a set a.oper_degree=0,a.sts=1,a.remark='',a.req_sys=1 
where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%com.cattsoft.im.component.domain.TxRmsServiceDOM%';*/

----3.���÷���ͨ�����쳣:�ᵥ����δ�ҵ�Ҫ��ȡ��¼!
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%���÷���ͨ�����쳣:�ᵥ����δ�ҵ�Ҫ��ȡ��¼!%';
--��������Ϣ��������

----4.�ƻ�:��Ӧ�ķ���ʵ��Ϊ��
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%��Ӧ�ķ���ʵ��Ϊ��%';
--����:p_imp_user_instance_mainten

select a.*,rowid from tmp_user_info_special a where a.user_id=87439524;

----5.���:���ط���ʵ����¼Ϊ��
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%���ط���ʵ����¼Ϊ��%';

----6.Connection timed out�������쳣��
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%Connection timed out%';
--����
/*update res_interface a set a.oper_degree=0,a.sts=1,a.remark='' 
where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%Connection timed out%';*/

select a.*,rowid from res_interface a where a.sps_so_nbr=8141511060895240082;
select a.*,rowid from tmp_user_info_special a where a.user_id=87439524;

p_imp_user_ins_special;