   SELECT * FROM   imp_exp_templet a WHERE        a.sts = 1; --
   
   select a.*, a.rowid
     from T_IMP_USER_EXAMPLE a
    WHERE a.serial_id = 1452734
      and a.status = 2;
 ---根据类型去跟对应存储
 
 pkg_user_example_imp.PORC_IMP_USER_EXAMPLE
