select a.*, a.rowid
  from res_interface a
 WHERE a.sps_so_nbr =8231508060872814191;

p_imp_user_instance_mainten  --订单同步，运行补服务实例的存储 REQ_SYS =1 时使用

sc_rms_lc.add_serv_ins -- 订单同步，运行补服务实例的存储 REQ_SYS =2 时使用

select * from res_work_item t where t.so_nbr = '8201508270876931018'
