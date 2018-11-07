 SELECT *
  FROM serv_acc_nbr a
 WHERE a.acc_nbr = '01035435'
   and a.local_net_id = 815;
 
  update serv_username
     set sts = 2
   where serv_id = 69888598
     and local_net_id = 815;
  update serv_cust
     set sts = 2
   where serv_id = 69888598
     and local_net_id = 815
     and sts = 1;
  update serv_acc_nbr
     set sts = 2
   where serv_id = 69888598
     and local_net_id = 815
     and sts = 1;
  update serv_addr
     set sts = 2
   where serv_id = 69888598
     and local_net_id = 815
     and sts = 1;
  delete serv_prod
   where serv_id = 69888598
     and sts = 1;
  delete serv_prod_prpty
   where serv_id = 69888598
     and local_net_id = 815
     and sts = 1;
  update serv
     set sts = 2
   where serv_id = 69888598
     and local_net_id = 815;
