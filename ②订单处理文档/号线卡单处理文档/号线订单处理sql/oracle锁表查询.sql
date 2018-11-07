--À¯±Ì≤È—Ø
SELECT object_name, machine, s.sid, s.serial# 
FROM gv$locked_object l, dba_objects o, gv$session s 
WHERE l.object_id°°= o.object_id 
AND l.session_id = s.SID AND O.OBJECT_NAME='CRM_XML_LOG'; 

-- Õ∑≈SESSION SQL: 
--alter system kill session 'sid, serial#'; 
ALTER system kill session '2214, 8353'; 
