get_zjh_info;


1.通过订单同步记录找到标准地址
2.通过标准地址查找 tenetrescover表，获取地址覆盖的分光器设备
3.通过分光器设备，查找odd表，查询Odd设备上联olt端口 pon_port字段;
4.查询pon口上Loid情况,查询port_loid表,如果对应的记录数为0，去号线系统手动生成LOID，
若记录状态全为30，表示无空闲LOID可用，请地市自行想办法处理
