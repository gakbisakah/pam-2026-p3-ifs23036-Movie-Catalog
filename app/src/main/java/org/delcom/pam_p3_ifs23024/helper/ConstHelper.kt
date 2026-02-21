package org.delcom.pam_p3_ifs23024.helper

class ConstHelper {
    // Route Names - dengan slash di depan agar konsisten dengan navigasi
    enum class RouteNames(val path: String) {
        Home(path = "/home"),
        Profile(path = "/profile"),
        Plants(path = "/plants"),
        PlantsDetail(path = "/plants/{name}"),
    }
}