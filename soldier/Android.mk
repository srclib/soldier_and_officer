LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:=				\
		main.cpp                  	\
		CommandListener.cpp	       	\
		NetlinkHandler.cpp		\
		NetlinkManager.cpp		\
		SoldierCommand.cpp		\
		SoldierController.cpp		\



LOCAL_MODULE:= soldier

LOCAL_C_INCLUDES := $(KERNEL_HEADERS) \
                    external/openssl/include \
                    external/stlport/stlport \
                    bionic \
                    bionic/libc/private \
                    $(call include-path-for, libhardware_legacy)/hardware_legacy

LOCAL_CFLAGS := -Werror=format -DLOG_NDEBUG=0

LOCAL_SHARED_LIBRARIES := libstlport libsysutils libcutils libnetutils \
                          libcrypto libhardware_legacy libmdnssd libdl

include $(BUILD_EXECUTABLE)

