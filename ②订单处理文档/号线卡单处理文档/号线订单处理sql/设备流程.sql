--�˵�
select * from menutree m where m.treenodename like '%����%';
select * from menutree m where m.treenodename like '%���%' ;
--�������͹���
select * from sys_dictionary_filter sd where sd.filtername='deviceResTempFilter' for update;
select * from sys_dictionary sd where sd.categoryid=20150204;
--ģ����� ,��ȥ�������� �����ô�ģ��
select * from res_templet_relation r where r.type=2;
--ģ������
--1.ģ���
select * from ho_res_def;
--2.ģ���ֶα�
select * from ho_prop_def h where h.res_def_id=100;
--���뵥��
select * from oo;
select * from res_apply;
select * from res_apply_detail;
select * from ho_res_inst h where h.so_nbr=6620151103190553569;
select * from ho_res_inst_prop h where h.res_inst_id=5473;
--���̱�
select * from wo where wo.oo_nbr=6620151103190553569;
--ģ��-�ֵ�ֵ��Ӧ��
select * from res_type_temp_relation;
--�洢��Ӧ��ϵ��
select * from ho_res_def_procedure_relation;

select * from step_org_user;


select * from staff;


