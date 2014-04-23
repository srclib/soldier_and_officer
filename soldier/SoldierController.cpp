/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#define LOG_NDEBUG 0

/*
 * The CommandListener, FrameworkListener don't allow for
 * multiple calls in parallel to reach the BandwidthController.
 * If they ever were to allow it, then netd/ would need some tweaking.
 */

#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>

#include <linux/netlink.h>
#include <linux/rtnetlink.h>
#include <linux/pkt_sched.h>

#define LOG_TAG "SoldierController"

#include <cutils/log.h>
#include <cutils/properties.h>

#include "SoldierController.h"

SoldierController:: SoldierController(void)
{

}

int  SoldierController::enableControl(bool enable) {
    int res = 0;
    char value[PROPERTY_VALUE_MAX];

    ALOGI("enableControl()");

    return res;
}
