package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository

import io.eduonline.devrush.devrushlmsmultiplatform.database.models.AchievementDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.LeaderDBO
import io.eduonline.devrush.devrushlmsmultiplatform.database.models.ProfileDBO
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader


internal fun ProfileDBO.toProfileData() = GetMeResponse(
    id = id,
    email = email,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    phone = phone,
    lang = lang,
    gender = gender,
    birthday = birthday,
    bonusBalance = bonusBalance,
    isEmailConfirmed = isEmailConfirmed,
    isSchoolEmailConfirmed = isSchoolEmailConfirmed,
    emailConfirmationSentDate = emailConfirmationSentDate,
    isPhoneConfirmed = isPhoneConfirmed,
    phoneConfirmationSentDate = phoneConfirmationSentDate,
    needChangePassword = needChangePassword,
    isAdmin = isAdmin,
    concurrencyStamp = concurrencyStamp,
    roles = roles,
    school = school,
    avatar = avatar,
    session = session,
    chatUserData = chatUserData,
    tags = tags,
    groups = groups
)

internal fun GetMeResponse.toProfileDBO() = ProfileDBO(
    id = id,
    email = email,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    phone = phone,
    lang = lang,
    gender = gender,
    birthday = birthday,
    bonusBalance = bonusBalance,
    isEmailConfirmed = isEmailConfirmed,
    isSchoolEmailConfirmed = isSchoolEmailConfirmed,
    emailConfirmationSentDate = emailConfirmationSentDate,
    isPhoneConfirmed = isPhoneConfirmed,
    phoneConfirmationSentDate = phoneConfirmationSentDate,
    needChangePassword = needChangePassword,
    isAdmin = isAdmin,
    concurrencyStamp = concurrencyStamp,
    roles = roles,
    school = school,
    avatar = avatar,
    session = session,
    chatUserData = chatUserData,
    tags = tags,
    groups = groups
)

internal fun LeaderDBO.toLeader() = Leader(
    place = place,
    score = score,
    student = student,
    achievements = achievements
)

internal fun Leader.toLeaderDBO() = LeaderDBO(
    place = place,
    score = score,
    student = student,
    achievements = achievements
)

internal fun AchievementDBO.toAchievement() = Achievement(
    id = id,
    title = title,
    description = description,
    systemImage = systemImage,
    customCloudKey = customCloudKey,
    type = type,
    subtype = subtype,
    imageType = imageType,
    gamificationStyle = gamificationStyle,
    systemAchievementId = systemAchievementId,
    imageCloudKey = imageCloudKey,
    points = points,
    count = count
)

internal fun Achievement.toAchievementDBO() = AchievementDBO(
    id = id,
    title = title,
    description = description,
    systemImage = systemImage,
    customCloudKey = customCloudKey,
    type = type,
    subtype = subtype,
    imageType = imageType,
    gamificationStyle = gamificationStyle,
    systemAchievementId = systemAchievementId,
    imageCloudKey = imageCloudKey,
    points = points,
    count = count
)