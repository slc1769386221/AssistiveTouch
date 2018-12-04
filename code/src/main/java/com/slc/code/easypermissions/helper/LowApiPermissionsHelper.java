package com.slc.code.easypermissions.helper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;

import com.slc.code.easypermissions.EasyPermissions;

/**
 * Permissions helper for apps built against API < 23, which do not need runtime permissions.
 */
class LowApiPermissionsHelper<T> extends PermissionHelper<T> {

    public LowApiPermissionsHelper(@NonNull T host, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.RationaleCallbacks rationaleCallbacks) {
        super(host, permissionCallbacks,rationaleCallbacks);
    }

    @Override
    public void directRequestPermissions(int requestCode, @NonNull String... perms) {
        throw new IllegalStateException("Should never be requesting permissions on API < 23!");
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String perm) {
        return false;
    }

    @Override
    public void showRequestPermissionRationale(@StyleRes int theme,
                                               int requestCode,
                                               @NonNull String... perms) {
        throw new IllegalStateException("Should never be requesting permissions on API < 23!");
    }

    @Override
    public Context getContext() {
        if (getHost() instanceof Activity) {
            return (Context) getHost();
        } else if (getHost() instanceof Fragment) {
            return ((Fragment) getHost()).getContext();
        } else if (getHost() instanceof android.app.Fragment) {
            return ((android.app.Fragment) getHost()).getActivity();
        } else {
            throw new IllegalStateException("Unknown host: " + getHost());
        }
    }
}
