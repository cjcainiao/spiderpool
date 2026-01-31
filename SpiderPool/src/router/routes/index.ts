//全局路由文件

import common from "./common";
import error from "./error";
import login from "./login";

export const routes = [
    ...common, 
    ...login,
    ...error
]
