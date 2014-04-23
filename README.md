soldier_and_officer
===================

@init.rc

# huyanwei {


service soldier /system/bin/soldier

      class core
      
      socket soldier stream 0660 root system
      
      user root
      
      group root
      
      
# huyanwei }
