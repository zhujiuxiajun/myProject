update res_interface a
   set a.oper_degree = 0, a.sts = 1, a.remark = ''
 WHERE a.sts = 8
   and a.remark like '%Connection timed out%';

update res_work_item a
   set a.sts = 1
 WHERE a.remarks like '%Connection timed out%';
 
update resource_interface a
   set a.sts = 1, a.remark = ''
 WHERE a.sts = 4
   and a.remark like '%Connection timed out%';
