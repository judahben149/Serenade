package com.judahben149.serenade.domain.models

import com.judahben149.serenade.utils.resourceUtils.DefaultColors.DARK_MUTED_BODY_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.DARK_MUTED_RGB
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.DARK_MUTED_TITLE_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.DARK_VIBRANT_BODY_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.DARK_VIBRANT_RGB
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.DARK_VIBRANT_TITLE_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.LIGHT_MUTED_BODY_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.LIGHT_MUTED_RGB
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.LIGHT_MUTED_TITLE_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.LIGHT_VIBRANT_BODY_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.LIGHT_VIBRANT_RGB
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.LIGHT_VIBRANT_TITLE_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.MUTED_BODY_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.MUTED_RGB
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.MUTED_TITLE_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.VIBRANT_BODY_TEXT_COLOR
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.VIBRANT_RGB
import com.judahben149.serenade.utils.resourceUtils.DefaultColors.VIBRANT_TITLE_TEXT_COLOR

data class SerenadeColor(
    val lightVibrantSwatch: SerenadeSwatch = SerenadeSwatch(
        LIGHT_VIBRANT_RGB,
        LIGHT_VIBRANT_TITLE_TEXT_COLOR,
        LIGHT_VIBRANT_BODY_TEXT_COLOR
    ),

    val vibrantSwatch: SerenadeSwatch = SerenadeSwatch(
        VIBRANT_RGB,
        VIBRANT_TITLE_TEXT_COLOR,
        VIBRANT_BODY_TEXT_COLOR
    ),

    val darkVibrantSwatch: SerenadeSwatch = SerenadeSwatch(
        DARK_VIBRANT_RGB,
        DARK_VIBRANT_TITLE_TEXT_COLOR,
        DARK_VIBRANT_BODY_TEXT_COLOR
    ),

    val lightMutedSwatch: SerenadeSwatch = SerenadeSwatch(
        LIGHT_MUTED_RGB,
        LIGHT_MUTED_TITLE_TEXT_COLOR,
        LIGHT_MUTED_BODY_TEXT_COLOR
    ),

    val mutedSwatch: SerenadeSwatch = SerenadeSwatch(
        MUTED_RGB,
        MUTED_TITLE_TEXT_COLOR,
        MUTED_BODY_TEXT_COLOR
    ),

    val darkMutedSwatch: SerenadeSwatch = SerenadeSwatch(
        DARK_MUTED_RGB,
        DARK_MUTED_TITLE_TEXT_COLOR,
        DARK_MUTED_BODY_TEXT_COLOR
    ),
)

data class SerenadeSwatch(
    val rgb: Int,
    val titleTextColor: Int,
    val bodyTextColor: Int,
)
