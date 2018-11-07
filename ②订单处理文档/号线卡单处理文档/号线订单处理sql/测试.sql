SELECT * FROM       device a WHERE     a.name = '网厅专用_金堂_海特NGN局向(810008)机房DSLAM';

select a.*,a.rowid from    dslam_device a WHERE a.id = 601710412; --dslam 小表  sub_type=6021 BAS_DEVICE_di/BAS_PORT

SELECT * FROM      port a WHERE         a.device_id = 601710412 and a.name = '00-00-00-48';

select a.*,a.rowid from an_device_port a WHERE      a.id = 118242613;
