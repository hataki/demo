### Volatile  
虚拟机级别  原语级别--读屏障/写屏障  loadfence/storefence  
功能： 可见性 指令重排序  
使用： dcl (double check lock )  
:scream_cat: :scream_cat: :scream_cat:   
:city_sunset::city_sunset::city_sunset::city_sunset:    
         
 
底层通过操作unsafe类实现，jdk11以上才能够直接操作；  

分配/释放内存  
c malloc/free ； c++ new/delete  

