update  physical_dn a set a.status = 10
 WHERE a.all_line_nbr >= 82363551
   and a.all_line_nbr <= 82363750
   and a.area_code = '028'
   and a.status = 29
