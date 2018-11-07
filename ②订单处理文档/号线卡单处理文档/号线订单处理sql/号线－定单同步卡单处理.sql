----1.当前环节并非待办环节
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%调用服务开通服务异常:当前环节并非待办环节！%';

----2.BSS的定单错判为IOM的定单
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%com.cattsoft.im.component.domain.TxRmsServiceDOM%';
--处理
/*update res_interface a set a.oper_degree=0,a.sts=1,a.remark='',a.req_sys=1 
where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%com.cattsoft.im.component.domain.TxRmsServiceDOM%';*/

----3.调用服务开通服务异常:提单表中未找到要提取记录!
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%调用服务开通服务异常:提单表中未找到要提取记录!%';
--处理：找信息化部处理

----4.移机:对应的服务实例为空
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%对应的服务实例为空%';
--处理:p_imp_user_instance_mainten

select a.*,rowid from tmp_user_info_special a where a.user_id=87439524;

----5.拆机:返回服务实例记录为空
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%返回服务实例记录为空%';

----6.Connection timed out（网络异常）
select * from res_interface a where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%Connection timed out%';
--处理
/*update res_interface a set a.oper_degree=0,a.sts=1,a.remark='' 
where a.oper_degree=2 and a.sts=8
and a.create_date>to_date('2015/11/01','YYYY/MM/DD')
and a.remark like '%Connection timed out%';*/

select a.*,rowid from res_interface a where a.sps_so_nbr=8141511060895240082;
select a.*,rowid from tmp_user_info_special a where a.user_id=87439524;

p_imp_user_ins_special;