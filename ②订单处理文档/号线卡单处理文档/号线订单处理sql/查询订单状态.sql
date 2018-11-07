select t.*, t.rowid
  from res_interface t
 where t.sps_so_nbr = '8116041901401167'; --订单同步


select t.*, t.rowid
  from res_interface_his t
 where t.sps_so_nbr = '8115062528929462'; --订单同步

/*REQ_SYS:1 长的  2 短的*/
 
select t.*, t.rowid  from res_work_item t
 where t.so_nbr = '8171604080933268894'  --配线配端口 
 
 8115110418328436
8115110418652939
 
select t.*, t.rowid
  from resource_interface t
 where t.so_nbr = '8171604080933268894' --资源归档

8115111832217645；8115111832338284;
p_imp_user_instance_mainten;  --长的
sc_rms_lc.add_serv_ins;  ---短的

select *  from so_his t where t.so_nbr = '8115083147141786'

