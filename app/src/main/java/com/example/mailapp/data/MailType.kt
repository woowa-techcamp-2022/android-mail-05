package com.example.mailapp.data

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.mailapp.R

enum class MailType(@StringRes val strId: Int, @IdRes val menuId: Int) {
    PRIMARY(
        R.string.action_primary,
        R.id.action_primary
    ),
    SOCIAL(
        R.string.action_social,
        R.id.action_social
    ),
    PROMOTIONS(
        R.string.action_promotions,
        R.id.action_promotions
    );

    companion object {
        fun getMailTypeById(@IdRes menuId: Int): MailType? {
            return values().find { it.menuId == menuId }
        }
    }
}