--菜单
select * from menutree m where m.treenodename like '%申请%';
select * from menutree m where m.treenodename like '%审核%' ;
--申请类型过滤
select * from sys_dictionary_filter sd where sd.filtername='deviceResTempFilter' for update;
select * from sys_dictionary sd where sd.categoryid=20150204;
--模板过滤 ,除去交资以外 都采用此模板
select * from res_templet_relation r where r.type=2;
--模板配置
--1.模板表
select * from ho_res_def;
--2.模板字段表
select * from ho_prop_def h where h.res_def_id=100;
--申请单表
select * from oo;
select * from res_apply;
select * from res_apply_detail;
select * from ho_res_inst h where h.so_nbr=6620151103190553569;
select * from ho_res_inst_prop h where h.res_inst_id=5473;
--流程表
select * from wo where wo.oo_nbr=6620151103190553569;
--模板-字典值对应表
select * from res_type_temp_relation;
--存储对应关系表
select * from ho_res_def_procedure_relation;

select * from step_org_user;


select * from staff;


