#!/usr/bin/env groovy

import com.example.Docker

def call(String user, String password, String host) {
    return new Docker(this).dockerLoginInToHost(user, password, host)
}
