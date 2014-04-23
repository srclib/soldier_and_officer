/*
 * Copyright (C) 2008 The Android Open Source Project
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

#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <dirent.h>
#include <errno.h>
#include <string.h>
#include <fcntl.h>
#include <linux/if.h>

#define LOG_TAG "CommandListener"

#include <cutils/properties.h>
#include <cutils/log.h>
#include <netutils/ifc.h>
#include <sysutils/SocketClient.h>

#include "CommandListener.h"
#include "ResponseCode.h"

#include "SoldierController.h"

SoldierController * CommandListener::sSoldierCtrl = NULL;

CommandListener::CommandListener() : FrameworkListener("soldier", false)
{
    registerCmd(new SoldierControlCmd());

    if (!sSoldierCtrl)
        sSoldierCtrl = new SoldierController();
}

CommandListener::SoldierControlCmd::SoldierControlCmd() : SoldierCommand("control")
{

}

int CommandListener::SoldierControlCmd::runCommand(SocketClient *cli, int argc, char **argv) 
{
    if (argc < 2) 
    {
        return 0;
    }

    ALOGV("ctrlcmd: argc=%d %s %s ...", argc, argv[0], argv[1]);

    if (!strcmp(argv[1], "enable")) {
        int rc = sSoldierCtrl->enableControl(true);
        return 0;

    }
    if (!strcmp(argv[1], "disable")) {
        int rc = sSoldierCtrl->enableControl(false);
        return 0;

    }
    cli->sendMsg(ResponseCode::CommandSyntaxError, "Unknown bandwidth cmd", false);
    return 0;
}

