select a.*, a.rowid
  from serv_acc_nbr a
 WHERE a.serv_id = 8115120849458430; 
/* a.acc_nbr = '01007554'
 and a.sts = 1; */--有数据并且sts=1表示有服务实例
select a.*,a.rowid from  serv_addr a WHERE    a.serv_id = 8115120849458430;
 select a.*,a.rowid from  serv  a WHERE a.serv_id = 8115122300495881;
select a.*,a.rowid from  serv_atom_rs_ins a WHERE       a.serv_id = 8115122300495881;
--2xx的atom_rs_ins_id放到atom_res_serv_ins表中查
select a.*,a.rowid from  serv_res a WHERE a.serv_id = 8115120849458430;  --sts=2是作废状态
select * from atom_res_serv_ins t where t.atom_rs_ins_id = '6070469415' 
--res_id 就是端口id

--下面两个表的数据应该配套
 select a.*, a.rowid
   from serv_atom_rs_ins a
  where a.serv_id = 8115120849458430;
  select a.*, a.rowid
    from comp_rs_ins_comp a
   WHERE a.comp_rs_ins_id = 6030048693;
