sc_rms_lc.add_serv_ins_operate;

insert into pc_serv_ins_operate
       select a.so_nbr,a.serv_id,'','',b.prod_id,'',a.cust_id,'','','','','','','',
       b.local_net_id,'','','',b.area_id,3,'',a.create_date,''
       from resource_interface a,so b where a.so_nbr=b.so_nbr 
       and a.create_date>=to_date('2015/11/01','YYYY/MM/DD')
       and b.change_serv_spec_id in(10,33,288,16,301,1025)
       and a.sts<>1
       and a.local_net_id = '822'--限制本地网
       and not exists(select 1 from serv c where a.serv_id=c.serv_id and c.sts=1)
       
       --先删除表中有的数据，再插入数据，加入限制本地网的条件,在跑上面的存储
