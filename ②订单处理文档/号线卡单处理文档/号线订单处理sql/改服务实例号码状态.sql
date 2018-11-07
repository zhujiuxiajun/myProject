
 
  update serv_acc_nbr a
     set a.sts = 2
   WHERE a.serv_id in (select a.serv_id
                         from serv_acc_nbr a
                        WHERE a.acc_nbr = '66150068'
                          and a.local_net_id = 810);
  update serv a
     set a.sts = 2
   WHERE a.serv_id in (select a.serv_id
                         from serv_acc_nbr a
                        WHERE a.acc_nbr = '66150068'
                          and a.local_net_id = 810);
  update serv_addr a
     set a.sts = 2
   WHERE a.serv_id in (select a.serv_id
                         from serv_acc_nbr a
                        WHERE a.acc_nbr = '66150068'
                          and a.local_net_id = 810);
  update serv_res a
     set a.sts = 2
   WHERE a.serv_id in (select a.serv_id
                         from serv_acc_nbr a
                        WHERE a.acc_nbr = '66150068'
                          and a.local_net_id = 810);
  update logical_dn a
     set a.status = 10
   WHERE a.all_line_nbr = '66150068'
     and a.local_net_id = 810;
  update physical_dn a
     set a.status = 10
   WHERE a.all_line_nbr = '66150068'
     and a.local_net_id = 810;
