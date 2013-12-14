package cn.com.shine.hotel.impl;
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.factory/factorycolortemp/colortemperatureid/"
                            + colorTmpId), vals, null, null);
        }
        }
        if (ret == -1) {
            System.out.println("update tbl_FactoryColorTemp ignored");
        }
        try {
        }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

     * 
     * @param sourceId
     * @return
     */
    public T_MS_COLOR_TEMPEX queryFactoryColorTempExData() {
        for (int sourceIdx = 0; sourceIdx < EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NUM
                .ordinal(); sourceIdx++) {
            Cursor cursor = getContentResolver().query(
                    Uri.parse("content://mstar.tv.factory/factorycolortempex"), null,
                    "InputSourceID = " + sourceIdx, null, "ColorTemperatureID");
            for (int colorTmpIdx = 0; colorTmpIdx < EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal(); colorTmpIdx++) {
                if (cursor.moveToNext()) {
                    m_stFactoryColorTempEx.astColorTempEx[colorTmpIdx][sourceIdx].redgain = cursor
                            .getInt(cursor.getColumnIndex("u16RedGain"));
                    m_stFactoryColorTempEx.astColorTempEx[colorTmpIdx][sourceIdx].greengain = cursor
                            .getInt(cursor.getColumnIndex("u16GreenGain"));
                    m_stFactoryColorTempEx.astColorTempEx[colorTmpIdx][sourceIdx].bluegain = cursor
                            .getInt(cursor.getColumnIndex("u16BlueGain"));
                    m_stFactoryColorTempEx.astColorTempEx[colorTmpIdx][sourceIdx].redoffset = cursor
                            .getInt(cursor.getColumnIndex("u16RedOffset"));
                    m_stFactoryColorTempEx.astColorTempEx[colorTmpIdx][sourceIdx].greenoffset = cursor
                            .getInt(cursor.getColumnIndex("u16GreenOffset"));
                    m_stFactoryColorTempEx.astColorTempEx[colorTmpIdx][sourceIdx].blueoffset = cursor
                            .getInt(cursor.getColumnIndex("u16BlueOffset"));
                }
        }
    }
            int colorTmpId) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("u16RedGain", model.redgain);
        vals.put("u16GreenGain", model.greengain);
        vals.put("u16BlueGain", model.bluegain);
        vals.put("u16RedOffset", model.redoffset);
        vals.put("u16GreenOffset", model.greenoffset);
        vals.put("u16BlueOffset", model.blueoffset);
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.factory/factorycolortempex/inputsourceid/"
                            + sourceId + "/colortemperatureid/" + colorTmpId), vals, null, null);
        }
        catch (SQLException e) {
            System.out.println("update tbl_FactoryColorTempEx ignored");
        }
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_FacrotyColorTempEx_IDX);
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param inputSrcType
     * @return
     */
    public T_MS_VIDEO queryAllVideoPara(int inputSrcType) {
        // query tbl_VideoSetting for T_MS_VIDEO videopara base info and for
        // T_MS_SUB_COLOR g_astSubColor of videoPara
        Cursor cursorVideo = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/videosetting/inputsrc/" + inputSrcType),
                null, null, null, null);
        while (cursorVideo.moveToNext()) {
            // videopara base info
            videopara.ePicture = EN_MS_PICTURE.values()[cursorVideo.getInt(cursorVideo
                    .getColumnIndex("ePicture"))];
            videopara.enARCType = MAPI_VIDEO_ARC_Type.values()[cursorVideo.getInt(cursorVideo
                    .getColumnIndex("enARCType"))];
            videopara.fOutput_RES = EN_DISPLAY_RES_TYPE.values()[cursorVideo.getInt(cursorVideo
                    .getColumnIndex("fOutput_RES"))];
            videopara.tvsys = MAPI_VIDEO_OUT_VE_SYS.values()[cursorVideo.getInt(cursorVideo
                    .getColumnIndex("tvsys"))];
            videopara.LastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[cursorVideo
                    .getInt(cursorVideo.getColumnIndex("LastVideoStandardMode"))];
            videopara.LastAudioStandardMode = AUDIOMODE_TYPE_.values()[cursorVideo
                    .getInt(cursorVideo.getColumnIndex("LastAudioStandardMode"))];
            videopara.eDynamic_Contrast = EN_MS_Dynamic_Contrast.values()[cursorVideo
                    .getInt(cursorVideo.getColumnIndex("eDynamic_Contrast"))];
            videopara.eFilm = EN_MS_FILM.values()[cursorVideo.getInt(cursorVideo
                    .getColumnIndex("eFilm"))];
            videopara.eTvFormat = EN_DISPLAY_TVFORMAT.values()[cursorVideo.getInt(cursorVideo
                    .getColumnIndex("eTvFormat"))];
            // T_MS_SUB_COLOR g_astSubColor of videoPara
            videopara.g_astSubColor.SubBrightness = (short) cursorVideo.getInt(cursorVideo
            videopara.g_astSubColor.SubContrast = (short) cursorVideo.getInt(cursorVideo
                    .getColumnIndex("u8SubContrast"));
        }
        // query tbl_PicMode_Setting for T_MS_PICTURE astPicture[] of videoPara
        Cursor cursorPicMode = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/picmode_setting"), null,
                "InputSrcType = " + inputSrcType, null, "PictureModeType");
        int picModeIdx = 0;
        int length = videopara.astPicture.length;
        while (cursorPicMode.moveToNext()) {
            if (picModeIdx > length - 1) {
                break;
            }
            videopara.astPicture[picModeIdx].backlight = (short) cursorPicMode.getInt(cursorPicMode
                    .getColumnIndex("u8Backlight"));
            videopara.astPicture[picModeIdx].contrast = (short) cursorPicMode.getInt(cursorPicMode
                    .getColumnIndex("u8Contrast"));
            videopara.astPicture[picModeIdx].brightness = (short) cursorPicMode
                    .getInt(cursorPicMode.getColumnIndex("u8Brightness"));
            videopara.astPicture[picModeIdx].saturation = (short) cursorPicMode
                    .getInt(cursorPicMode.getColumnIndex("u8Saturation"));
            videopara.astPicture[picModeIdx].sharpness = (short) cursorPicMode.getInt(cursorPicMode
                    .getColumnIndex("u8Sharpness"));
            videopara.astPicture[picModeIdx].hue = (short) cursorPicMode.getInt(cursorPicMode
                    .getColumnIndex("u8Hue"));
            videopara.astPicture[picModeIdx].eColorTemp = EN_MS_COLOR_TEMP.values()[cursorPicMode
                    .getInt(cursorPicMode.getColumnIndex("eColorTemp"))];
            videopara.astPicture[picModeIdx].eVibrantColour = EN_MS_PIC_ADV.values()[cursorPicMode
                    .getInt(cursorPicMode.getColumnIndex("eVibrantColour"))];
            videopara.astPicture[picModeIdx].ePerfectClear = EN_MS_PIC_ADV.values()[cursorPicMode
                    .getInt(cursorPicMode.getColumnIndex("ePerfectClear"))];
            videopara.astPicture[picModeIdx].eDynamicContrast = EN_MS_PIC_ADV.values()[cursorPicMode
            videopara.astPicture[picModeIdx].eDynamicBacklight = EN_MS_PIC_ADV.values()[cursorPicMode
                    .getInt(cursorPicMode.getColumnIndex("eDynamicBacklight"))];
            picModeIdx++;
        }
        cursorPicMode.close();
        // query tbl_NRMode for T_MS_NR_MODE eNRMode[] of videoPara
        Cursor cursorNRMode = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/nrmode"), null,
                "InputSrcType = " + inputSrcType, null, "NRMode");
        int NRModeIdx = 0;
        int length1 = videopara.eNRMode.length;
        while (cursorNRMode.moveToNext()) {
            if (NRModeIdx > length1 - 1) {
                break;
            }
            videopara.eNRMode[NRModeIdx].eNR = EN_MS_NR.values()[cursorNRMode.getInt(cursorNRMode
                    .getColumnIndex("eNR"))];
            videopara.eNRMode[NRModeIdx].eMPEG_NR = EN_MS_MPEG_NR.values()[cursorNRMode
                    .getInt(cursorNRMode.getColumnIndex("eMPEG_NR"))];
            NRModeIdx++;
        }
        cursorNRMode.close();
        // query tbl_ThreeDVideoMode for ThreeD_Video_MODE ThreeDVideoMode of
        // videoPara
        Cursor cursor3DMode = getContentResolver()
                .query(Uri.parse("content://mstar.tv.usersetting/threedvideomode/inputsrc/"
                        + inputSrcType), null, null, null, null);
        if (cursor3DMode.moveToFirst()) {
            videopara.ThreeDVideoMode.eThreeDVideo = EN_ThreeD_Video.values()[cursor3DMode
                    .getInt(cursor3DMode.getColumnIndex("eThreeDVideo"))];
            // videopara.ThreeDVideoMode.eThreeDVideoDisplayFormat =
            // EN_ThreeD_Video_DISPLAYFORMAT.values()[cursor3DMode
            // .getInt(cursor3DMode.getColumnIndex("eThreeDVideoDisplayFormat"))];
            // videopara.ThreeDVideoMode.eThreeDVideo3DTo2D =
            // EN_ThreeD_Video_3DTO2D.values()[cursor3DMode.getInt(cursor3DMode
            videopara.ThreeDVideoMode.eThreeDVideo3DDepth = EN_ThreeD_Video_3DDEPTH.values()[cursor3DMode
                    .getInt(cursor3DMode.getColumnIndex("eThreeDVideo3DDepth"))];
            videopara.ThreeDVideoMode.eThreeDVideo3DOffset = EN_ThreeD_Video_3DOFFSET.values()[cursor3DMode
                    .getInt(cursor3DMode.getColumnIndex("eThreeDVideo3DOffset"))];
            videopara.ThreeDVideoMode.eThreeDVideoAutoStart = EN_ThreeD_Video_AUTOSTART.values()[cursor3DMode
                    .getInt(cursor3DMode.getColumnIndex("eThreeDVideoAutoStart"))];
            videopara.ThreeDVideoMode.eThreeDVideo3DOutputAspect = EN_ThreeD_Video_3DOUTPUTASPECT
                    .values()[cursor3DMode.getInt(cursor3DMode
                    .getColumnIndex("eThreeDVideo3DOutputAspect"))];
            videopara.ThreeDVideoMode.eThreeDVideoLRViewSwitch = EN_ThreeD_Video_LRVIEWSWITCH
                    .values()[cursor3DMode.getInt(cursor3DMode
                    .getColumnIndex("eThreeDVideoLRViewSwitch"))];
        }
        cursor3DMode.close();
        // query tbl_ThreeDVideoMode for ThreeD_Video_MODE ThreeDVideoMode
        // eThreeDVideoSelfAdaptiveDetect of
        // videoPara
        Cursor cursor3DSelfAdaptiveMode = getContentResolver()
                .query(Uri.parse("content://mstar.tv.usersetting/threedvideomode/inputsrc/"
                        + inputSrcType), null, null, null, null);
        if (cursor3DSelfAdaptiveMode.moveToFirst()) {
            videopara.ThreeDVideoMode.eThreeDVideoSelfAdaptiveDetect = EN_ThreeD_Video_SELFADAPTIVE_DETECT
                    .values()[cursor3DSelfAdaptiveMode.getInt(cursor3DSelfAdaptiveMode
                    .getColumnIndex("eThreeDVideoSelfAdaptiveDetect"))];
        }
        cursor3DSelfAdaptiveMode.close();
        // query tbl_UserOverScanMode for T_MS_OVERSCAN_SETTING_USER
        // stUserOverScanMode of videoPara
        Cursor cursorUserOverScanMode = getContentResolver().query(
                        + inputSrcType), null, null, null, null);
        if (cursorUserOverScanMode.moveToFirst()) {
            videopara.stUserOverScanMode.OverScanHposition = (short) cursorUserOverScanMode
                    .getInt(cursorUserOverScanMode.getColumnIndex("OverScanHposition"));
            videopara.stUserOverScanMode.OverScanVposition = (short) cursorUserOverScanMode
                    .getInt(cursorUserOverScanMode.getColumnIndex("OverScanVposition"));
            videopara.stUserOverScanMode.OverScanHRatio = (short) cursorUserOverScanMode
                    .getInt(cursorUserOverScanMode.getColumnIndex("OverScanHRatio"));
            videopara.stUserOverScanMode.OverScanVRatio = (short) cursorUserOverScanMode
                    .getInt(cursorUserOverScanMode.getColumnIndex("OverScanVRatio"));
        }
        cursorUserOverScanMode.close();
        return videopara;
    }

        int value = 0;
        Cursor cursor3DMode = getContentResolver()
                .query(Uri.parse("content://mstar.tv.usersetting/threedvideomode/inputsrc/"
                        + inputSrcType), null, null, null, null);
        if (cursor3DMode.moveToFirst()) {
            value = cursor3DMode.getInt(cursor3DMode.getColumnIndex("eThreeDVideoLRViewSwitch"));
        }
        return value;
    }

        EN_ThreeD_Video_DISPLAYFORMAT threeDVideoDisplayFormat = EN_ThreeD_Video_DISPLAYFORMAT.DB_ThreeD_Video_DISPLAYFORMAT_NONE;
        Cursor cursor3DMode = getContentResolver()
                .query(Uri.parse("content://mstar.tv.usersetting/threedvideomode/inputsrc/"
                        + inputSrcType), null, null, null, null);
        if (cursor3DMode.moveToFirst()) {
            threeDVideoDisplayFormat = EN_ThreeD_Video_DISPLAYFORMAT.values()[cursor3DMode
                    .getInt(cursor3DMode.getColumnIndex("eThreeDVideoDisplayFormat"))];
        }
        return threeDVideoDisplayFormat;
    }

            int value) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        switch (eIndex) {
            case MS_VIDEOITEM_BRIGHTNESS:
                vals.put("u8Brightness", value);
            break;
            case MS_VIDEOITEM_CONTRAST:
                vals.put("u8Contrast", value);
            break;
            case MS_VIDEOITEM_HUE:
                vals.put("u8Hue", value);
            break;
            case MS_VIDEOITEM_SATURATION:
                vals.put("u8Saturation", value);
            break;
            case MS_VIDEOITEM_SHARPNESS:
                vals.put("u8Sharpness", value);
            break;
            case MS_VIDEOITEM_BACKLIGHT:
                vals.put("u8Backlight", value);
            default:
            break;
        }
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/picmode_setting/inputsrc/"
                            + inputSrcType + "/picmode/" + pictureModeType), vals, null, null);
        }
        catch (SQLException e) {
        }
        if (ret == -1) {
            System.out.println("update tbl_PicMode_Setting ignored");
        Cursor cursorPicMode = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/picmode_setting/inputsrc/" + inputSrcType
                        + "/picmode/" + pictureModeType), null, null, null, null);
        cursorPicMode.moveToFirst();
        int value = 0;
        switch (eIndex) {
            case MS_VIDEOITEM_BRIGHTNESS:
                value = cursorPicMode.getInt(cursorPicMode.getColumnIndex("u8Brightness"));
            break;
            case MS_VIDEOITEM_CONTRAST:
                value = cursorPicMode.getInt(cursorPicMode.getColumnIndex("u8Contrast"));
            break;
            case MS_VIDEOITEM_HUE:
                value = cursorPicMode.getInt(cursorPicMode.getColumnIndex("u8Hue"));
            break;
            case MS_VIDEOITEM_SATURATION:
                value = cursorPicMode.getInt(cursorPicMode.getColumnIndex("u8Saturation"));
            break;
            case MS_VIDEOITEM_SHARPNESS:
                value = cursorPicMode.getInt(cursorPicMode.getColumnIndex("u8Sharpness"));
            break;
            case MS_VIDEOITEM_BACKLIGHT:
                value = cursorPicMode.getInt(cursorPicMode.getColumnIndex("u8Backlight"));
            break;
            default:
            break;
        }
        return value;
    }

     * // update T_MS_VIDEO videopara base info
     * 
     * @param model
     * @param inputSrcType
     */
    public void updateVideoBasePara(T_MS_VIDEO model, int inputSrcType) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("ePicture", model.ePicture.ordinal());
        // vals.put("u8SubBrightness", model.);
        // vals.put("u8SubContrast", model.);
        vals.put("enARCType", model.enARCType.ordinal());
        vals.put("fOutput_RES", model.fOutput_RES.ordinal());
        vals.put("tvsys", model.tvsys.ordinal());
        vals.put("LastVideoStandardMode", model.LastVideoStandardMode.ordinal());
        vals.put("LastAudioStandardMode", model.LastAudioStandardMode.ordinal());
        vals.put("eDynamic_Contrast", model.eDynamic_Contrast.ordinal());
        vals.put("eFilm", model.eFilm.ordinal());
        vals.put("eTvFormat", model.eTvFormat.ordinal());
        try {
                    Uri.parse("content://mstar.tv.usersetting/videosetting/inputsrc/"
                            + inputSrcType), vals, null, null);
        }
            System.out.println("update tbl_VideoSetting ignored");
        }
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_VideoSetting_IDX);
        }
            e.printStackTrace();
        }
     * // update T_MS_PICTURE astPicture[i] of videopara
     * 
     * @param model
     * @param inputSrcType
     * @param pictureModeType
     */
    public void updateVideoAstPicture(T_MS_PICTURE model, int inputSrcType, int pictureModeType) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("u8Backlight", model.backlight);
        vals.put("u8Contrast", model.contrast);
        vals.put("u8Brightness", model.brightness);
        vals.put("u8Saturation", model.saturation);
        vals.put("u8Sharpness", model.sharpness);
        vals.put("u8Hue", model.hue);
        vals.put("eColorTemp", model.eColorTemp.ordinal());
        vals.put("eVibrantColour", model.eVibrantColour.ordinal());
        vals.put("ePerfectClear", model.ePerfectClear.ordinal());
        vals.put("eDynamicContrast", model.eDynamicContrast.ordinal());
        vals.put("eDynamicBacklight", model.eDynamicBacklight.ordinal());
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/picmode_setting/inputsrc/"
                            + inputSrcType + "/picmode/" + pictureModeType), vals, null, null);
        }
        catch (SQLException e) {
        }
        if (ret == -1) {
            System.out.println("update tbl_PicMode_Setting ignored");
        }
        try {
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_PicMode_Setting_IDX);
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
        }
    }

     * // update tbl_NRMode with T_MS_NR_MODE eNRMode[i] of videopara
     * 
     * @param model
     * @param inputSrcType
     * @param NRModeIdx
     */
    public void updateVideoNRMode(T_MS_NR_MODE model, int inputSrcType, int NRModeIdx) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("eNR", model.eNR.ordinal());
        vals.put("eMPEG_NR", model.eMPEG_NR.ordinal());
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/nrmode/nrmode/" + NRModeIdx
                            + "/inputsrc/" + inputSrcType), vals, null, null);
        }
        catch (SQLException e) {
        }
        if (ret == -1) {
            System.out.println("update tbl_NRMode ignored");
        }
        try {
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_NRMode_IDX);
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }

     * // update tbl_VideoSetting with T_MS_SUB_COLOR g_astSubColor of videopara
     * 
     * @param model
     * @param inputSrcType
     */
    public void updateVideoAstSubColor(T_MS_SUB_COLOR model, int inputSrcType) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("u8SubBrightness", model.SubBrightness);
        vals.put("u8SubContrast", model.SubContrast);
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/videosetting/inputsrc/"
                            + inputSrcType), vals, null, null);
        }
        }
            System.out.println("update tbl_VideoSetting ignored");
        }
        try {
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_VideoSetting_IDX);
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("eThreeDVideo", model.eThreeDVideo.ordinal());
        vals.put("eThreeDVideoSelfAdaptiveDetect", model.eThreeDVideoSelfAdaptiveDetect.ordinal());
        vals.put("eThreeDVideoDisplayFormat", model.eThreeDVideoDisplayFormat.ordinal());
        vals.put("eThreeDVideo3DTo2D", model.eThreeDVideo3DTo2D.ordinal());
        vals.put("eThreeDVideo3DDepth", model.eThreeDVideo3DDepth.ordinal());
        vals.put("eThreeDVideo3DOffset", model.eThreeDVideo3DOffset.ordinal());
        vals.put("eThreeDVideoAutoStart", model.eThreeDVideoAutoStart.ordinal());
        vals.put("eThreeDVideo3DOutputAspect", model.eThreeDVideo3DOutputAspect.ordinal());
        vals.put("eThreeDVideoLRViewSwitch", model.eThreeDVideoLRViewSwitch.ordinal());
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/threedvideomode/inputsrc/"
                            + inputSrcType), vals, null, null);
        }
            System.out.println("update tbl_ThreeDVideoMode ignored");
        }
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_ThreeDVideoMode_IDX);
        }
            e.printStackTrace();
        }
     * 
     * @param model
     * @param inputSrcType
     */
    public void updateVideo3DAdaptiveDetectMode(EN_ThreeD_Video_SELFADAPTIVE_DETECT model,
            int inputSrcType) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("eThreeDVideoSelfAdaptiveDetect", model.ordinal());
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/threedvideomode/inputsrc/"
                            + inputSrcType), vals, null, null);
        }
            System.out
                    .println("update tbl_ThreeDVideoMode field eThreeDVideoSelfAdaptiveDetect ignored");
        }
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_ThreeDVideoMode_IDX);
        }
            e.printStackTrace();
        }
     * 
     * @param model
     * @param inputSrcType
     */
    public void updateVideo3DDisplayFormat(EN_ThreeD_Video_DISPLAYFORMAT model, int inputSrcType) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("eThreeDVideoDisplayFormat", model.ordinal());
        try {
                            + inputSrcType), vals, null, null);
        }
                    .println("update tbl_ThreeDVideoMode field eThreeDVideoDisplayFormat ignored");
        }
        }
            e.printStackTrace();
        }
     * // update tbl_UserOverScanMode with T_MS_OVERSCAN_SETTING_USER stUserOverScanMode of
     * videopara
     * 
     * @param model
     * @param inputSrcType
     */
    public void updateVideoUserOverScanMode(T_MS_OVERSCAN_SETTING_USER model, int inputSrcType) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("OverScanHposition", model.OverScanHposition);
        vals.put("OverScanVposition", model.OverScanVposition);
        vals.put("OverScanHRatio", model.OverScanHRatio);
        vals.put("OverScanVRatio", model.OverScanVRatio);
        try {
            ret = getContentResolver().update(
                            + inputSrcType), vals, null, null);
        }
        }
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_UserOverScanMode_IDX);
        }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    public T_MS_COLOR_TEMP_DATA queryUsrColorTmpData() {
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/usercolortemp"), null, null, null, null);
        int i = 0;
        while (cursor.moveToNext()) {
            if (i > (EnumInputSource.E_INPUT_SOURCE_NUM.ordinal() - 1))
                break;
            stUsrColorTemp.redgain = (short) cursor.getInt(cursor.getColumnIndex("u8RedGain"));
            stUsrColorTemp.greengain = (short) cursor.getInt(cursor.getColumnIndex("u8GreenGain"));
            stUsrColorTemp.bluegain = (short) cursor.getInt(cursor.getColumnIndex("u8BlueGain"));
            stUsrColorTemp.redoffset = (short) cursor.getInt(cursor.getColumnIndex("u8RedOffset"));
            stUsrColorTemp.greenoffset = (short) cursor.getInt(cursor
                    .getColumnIndex("u8GreenOffset"));
            stUsrColorTemp.blueoffset = (short) cursor
                    .getInt(cursor.getColumnIndex("u8BlueOffset"));
            i++;
        }
        cursor.close();
    }

        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("u8RedGain", model.redgain);
        vals.put("u8GreenGain", model.greengain);
        vals.put("u8BlueGain", model.bluegain);
        vals.put("u8RedOffset", model.redoffset);
        vals.put("u8GreenOffset", model.greenoffset);
        vals.put("u8BlueOffset", model.blueoffset);
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/usercolortemp"), vals, null, null);
        }
        }
            System.out.println("update tbl_UserColorTemp ignored");
        }
        try {
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_USER_COLORTEMP_IDX);
        }
        catch (TvCommonException e) {
            e.printStackTrace();
        }
        Cursor cursor = getContentResolver()
                .query(Uri.parse("content://mstar.tv.usersetting/usercolortempex"), null, null,
                        null, null);
        int i = 0;
        while (cursor.moveToNext()) {
            if (i > (EnumInputSource.E_INPUT_SOURCE_NUM.ordinal() - 1))
                break;
            stUsrColorTempEx[i].redgain = cursor.getInt(cursor.getColumnIndex("u16RedGain"));
            stUsrColorTempEx[i].greengain = cursor.getInt(cursor.getColumnIndex("u16GreenGain"));
            stUsrColorTempEx[i].bluegain = cursor.getInt(cursor.getColumnIndex("u16BlueGain"));
            stUsrColorTempEx[i].redoffset = cursor.getInt(cursor.getColumnIndex("u16RedOffset"));
            stUsrColorTempEx[i].greenoffset = cursor
                    .getInt(cursor.getColumnIndex("u16GreenOffset"));
            stUsrColorTempEx[i].blueoffset = cursor.getInt(cursor.getColumnIndex("u16BlueOffset"));
            i++;
        }
        return stUsrColorTempEx;
    }
        long ret = -1;
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/usercolortempex/" + colorTmpIdx),
                    vals, null, null);
        }
        }
        if (ret == -1) {
            System.out.println("update tbl_UserColorTempEx ignored");
        }
        try {
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public MS_USER_SYSTEM_SETTING queryUserSysSetting() {
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/systemsetting"), null, null, null, null);
        if (cursor.moveToFirst()) {
            stUsrData.fRunInstallationGuide = cursor.getInt(cursor
                    .getColumnIndex("fRunInstallationGuide")) == 0 ? false : true;
            stUsrData.fNoChannel = cursor.getInt(cursor.getColumnIndex("fNoChannel")) == 0 ? false
                    : true;
            stUsrData.bDisableSiAutoUpdate = cursor.getInt(cursor
                    .getColumnIndex("bDisableSiAutoUpdate")) == 0 ? false : true;
            stUsrData.enInputSourceType = EnumInputSource.values()[cursor.getInt(cursor
                    .getColumnIndex("enInputSourceType"))];
            stUsrData.Country = MEMBER_COUNTRY.values()[cursor.getInt(cursor
                    .getColumnIndex("Country"))];
            stUsrData.enCableOperators = EN_CABLE_OPERATORS.values()[cursor.getInt(cursor
                    .getColumnIndex("enCableOperators"))];
            stUsrData.enSatellitePlatform = EN_SATELLITE_PLATFORM.values()[cursor.getInt(cursor
                    .getColumnIndex("enSatellitePlatform"))];
            // stUsrData. = (short)
            // cursor.getInt(cursor.getColumnIndex("u16NetworkId"));
            EnumLanguage eLang = EnumLanguage.values()[cursor.getInt(cursor
                    .getColumnIndex("Language"))];
            if (eLang == EnumLanguage.E_ENGLISH)
                stUsrData.enLanguage = EnumLanguage.E_ENGLISH;
            else if (eLang == EnumLanguage.E_CHINESE)
                stUsrData.enLanguage = EnumLanguage.E_CHINESE;
            else if (eLang == EnumLanguage.E_ACHINESE)
                stUsrData.enLanguage = EnumLanguage.E_ACHINESE;
            else {
                stUsrData.enLanguage = EnumLanguage.E_CHINESE;
                ContentValues vals = new ContentValues();
                vals.put("Language", stUsrData.enLanguage.ordinal());
                try {
                    getContentResolver().update(
                            Uri.parse("content://mstar.tv.usersetting/systemsetting"), vals, null,
                            null);
                }
                }
            }
            // stUsrData. = (short)
            // cursor.getInt(cursor.getColumnIndex("en3DARC"));
            stUsrData.enSPDIFMODE = SPDIF_TYPE.values()[cursor.getInt(cursor
                    .getColumnIndex("enSPDIFMODE"))];
            stUsrData.fSoftwareUpdate = (short) cursor.getInt(cursor
                    .getColumnIndex("fSoftwareUpdate"));
            stUsrData.u8OADTime = (short) cursor.getInt(cursor.getColumnIndex("U8OADTime"));
            stUsrData.fOADScanAfterWakeup = (short) cursor.getInt(cursor
                    .getColumnIndex("fOADScanAfterWakeup"));
            stUsrData.fAutoVolume = (short) cursor.getInt(cursor.getColumnIndex("fAutoVolume"));
            stUsrData.fDcPowerOFFMode = (short) cursor.getInt(cursor
                    .getColumnIndex("fDcPowerOFFMode"));
            stUsrData.DtvRoute = (short) cursor.getInt(cursor.getColumnIndex("DtvRoute"));
            stUsrData.ScartOutRGB = (short) cursor.getInt(cursor.getColumnIndex("ScartOutRGB"));
            stUsrData.U8Transparency = (short) cursor.getInt(cursor
                    .getColumnIndex("U8Transparency"));
            stUsrData.u32MenuTimeOut = cursor.getLong(cursor.getColumnIndex("u32MenuTimeOut"));
            stUsrData.AudioOnly = (short) cursor.getInt(cursor.getColumnIndex("AudioOnly"));
            stUsrData.bEnableWDT = (short) cursor.getInt(cursor.getColumnIndex("bEnableWDT"));
            stUsrData.u8FavoriteRegion = (short) cursor.getInt(cursor
                    .getColumnIndex("u8FavoriteRegion"));
            stUsrData.u8Bandwidth = (short) cursor.getInt(cursor.getColumnIndex("u8Bandwidth"));
            stUsrData.u8TimeShiftSizeType = (short) cursor.getInt(cursor
                    .getColumnIndex("u8TimeShiftSizeType"));
            stUsrData.fOadScan = (short) cursor.getInt(cursor.getColumnIndex("fOadScan"));
            stUsrData.bEnablePVRRecordAll = (short) cursor.getInt(cursor
                    .getColumnIndex("bEnablePVRRecordAll"));
                    .getColumnIndex("u8ColorRangeMode"));
            stUsrData.u8HDMIAudioSource = (short) cursor.getInt(cursor
                    .getColumnIndex("u8HDMIAudioSource"));
            stUsrData.bEnableAlwaysTimeshift = (short) cursor.getInt(cursor
                    .getColumnIndex("bEnableAlwaysTimeshift"));
            stUsrData.eSUPER = EN_MS_SUPER.values()[cursor.getInt(cursor.getColumnIndex("eSUPER"))];
            stUsrData.bUartBus = cursor.getInt(cursor.getColumnIndex("bUartBus")) == 0 ? false
                    : true;
            stUsrData.m_AutoZoom = (short) cursor.getInt(cursor.getColumnIndex("m_AutoZoom"));
            stUsrData.bOverScan = cursor.getInt(cursor.getColumnIndex("bOverScan")) == 0 ? false
                    : true;
            stUsrData.m_u8BrazilVideoStandardType = (short) cursor.getInt(cursor
                    .getColumnIndex("m_u8BrazilVideoStandardType"));
            stUsrData.m_u8SoftwareUpdateMode = (short) cursor.getInt(cursor
                    .getColumnIndex("m_u8SoftwareUpdateMode"));
            stUsrData.u32OSD_Active_Time = cursor.getLong(cursor.getColumnIndex("OSD_Active_Time"));
            stUsrData.m_MessageBoxExist = cursor.getInt(cursor.getColumnIndex("m_MessageBoxExist")) == 0 ? false
                    : true;
            stUsrData.u16LastOADVersion = cursor.getInt(cursor.getColumnIndex("u16LastOADVersion"));
            stUsrData.bEnableAutoChannelUpdate = cursor.getInt(cursor
                    .getColumnIndex("bEnableAutoChannelUpdate")) == 0 ? false : true;
            stUsrData.eChSwMode = cursor.getInt(cursor.getColumnIndex("bATVChSwitchFreeze")) == 0 ? EN_MS_CHANNEL_SWITCH_MODE.MS_CHANNEL_SWM_BLACKSCREEN
                    : EN_MS_CHANNEL_SWITCH_MODE.MS_CHANNEL_SWM_FREEZE;
        }
        cursor.close();
        return stUsrData;
    }

        long ret = -1;
        EnumLanguage eLang;
        ContentValues vals = new ContentValues();
        vals.put("fRunInstallationGuide", model.fRunInstallationGuide ? 1 : 0);
        vals.put("fNoChannel", model.fNoChannel ? 1 : 0);
        vals.put("bDisableSiAutoUpdate", model.bDisableSiAutoUpdate ? 1 : 0);
        // vals.put("enInputSourceType", model.enInputSourceType.ordinal());
        vals.put("Country", model.Country.ordinal());
        vals.put("enCableOperators", model.enCableOperators.ordinal());
        vals.put("enSatellitePlatform", model.enSatellitePlatform.ordinal());
        // vals.put("u16NetworkId", model.);
        if (model.enLanguage == EnumLanguage.E_ENGLISH) {
            eLang = EnumLanguage.E_ENGLISH;
        }
        else if (model.enLanguage == EnumLanguage.E_ACHINESE) {
            eLang = EnumLanguage.E_ACHINESE;
        }
        else {
            eLang = EnumLanguage.E_CHINESE;
        }
        vals.put("Language", eLang.ordinal());
        // vals.put("en3DARC", model);
        vals.put("enSPDIFMODE", model.enSPDIFMODE.ordinal());
        vals.put("fSoftwareUpdate", model.fSoftwareUpdate);
        vals.put("U8OADTime", model.u8OADTime);
        vals.put("fOADScanAfterWakeup", model.fOADScanAfterWakeup);
        vals.put("fAutoVolume", model.fAutoVolume);
        vals.put("fDcPowerOFFMode", model.fDcPowerOFFMode);
        vals.put("DtvRoute", model.DtvRoute);
        vals.put("ScartOutRGB", model.ScartOutRGB);
        vals.put("U8Transparency", model.U8Transparency);
        vals.put("u32MenuTimeOut", model.u32MenuTimeOut);
        vals.put("AudioOnly", model.AudioOnly);
        vals.put("bEnableWDT", model.bEnableWDT);
        vals.put("u8FavoriteRegion", model.u8FavoriteRegion);
        vals.put("u8Bandwidth", model.u8Bandwidth);
        vals.put("u8TimeShiftSizeType", model.u8TimeShiftSizeType);
        vals.put("fOadScan", model.fOadScan);
        vals.put("bEnablePVRRecordAll", model.bEnablePVRRecordAll);
        vals.put("u8HDMIAudioSource", model.u8HDMIAudioSource);
        vals.put("bEnableAlwaysTimeshift", model.bEnableAlwaysTimeshift);
        vals.put("eSUPER", model.eSUPER.ordinal());
        vals.put("bUartBus", model.bUartBus ? 1 : 0);
        vals.put("m_AutoZoom", model.m_AutoZoom);
        vals.put("bOverScan", model.bOverScan ? 1 : 0);
        vals.put("m_u8BrazilVideoStandardType", model.m_u8BrazilVideoStandardType);
        vals.put("m_u8SoftwareUpdateMode", model.m_u8SoftwareUpdateMode);
        vals.put("OSD_Active_Time", model.u32OSD_Active_Time);
        vals.put("m_MessageBoxExist", model.m_MessageBoxExist ? 1 : 0);
        vals.put("u16LastOADVersion", model.u16LastOADVersion);
        vals.put("bEnableAutoChannelUpdate", model.bEnableAutoChannelUpdate ? 1 : 0);
        vals.put("bATVChSwitchFreeze", model.eChSwMode.ordinal());
        try {
            ret = getContentResolver().update(
        }
        }
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_SystemSetting_IDX);
        }
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    public SPDIF_TYPE getSpdifMode() {
        stUsrData = queryUserSysSetting();
        return stUsrData.enSPDIFMODE;
    }
    public void setSpdifMode(SPDIF_TYPE mode) {
        stUsrData = queryUserSysSetting();
        stUsrData.enSPDIFMODE = mode;
        updateUserSysSetting(stUsrData);
    }
    public MS_USER_SUBTITLE_SETTING queryUserSubtitleSetting() {
        Cursor cursor = getContentResolver()
                .query(Uri.parse("content://mstar.tv.usersetting/subtitlesetting"), null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            EnumLanguage eLang1 = EnumLanguage.values()[cursor.getInt(cursor
                    .getColumnIndex("SubtitleDefaultLanguage"))];
            EnumLanguage eLang2 = EnumLanguage.values()[cursor.getInt(cursor
                    .getColumnIndex("SubtitleDefaultLanguage_2"))];
            if (eLang1 == EnumLanguage.E_ENGLISH)
                stSubtitleSet.SubtitleDefaultLanguage = EnumLanguage.E_ENGLISH;
            else if (eLang1 == EnumLanguage.E_CHINESE)
                stSubtitleSet.SubtitleDefaultLanguage = EnumLanguage.E_CHINESE;
            else if (eLang1 == EnumLanguage.E_ACHINESE)
                stSubtitleSet.SubtitleDefaultLanguage = EnumLanguage.E_ACHINESE;
            else {
                stSubtitleSet.SubtitleDefaultLanguage = EnumLanguage.E_CHINESE;
                ContentValues vals = new ContentValues();
                vals.put("SubtitleDefaultLanguage", stUsrData.enLanguage.ordinal());
                try {
                            Uri.parse("content://mstar.tv.usersetting/subtitlesetting"), vals,
                            null, null);
                }
                }
            else if (eLang2 == EnumLanguage.E_CHINESE)
                stSubtitleSet.SubtitleDefaultLanguage_2 = EnumLanguage.E_CHINESE;
            else if (eLang2 == EnumLanguage.E_ACHINESE)
                stSubtitleSet.SubtitleDefaultLanguage_2 = EnumLanguage.E_ACHINESE;
            else {
                stSubtitleSet.SubtitleDefaultLanguage_2 = EnumLanguage.E_CHINESE;
                ContentValues vals = new ContentValues();
                vals.put("SubtitleDefaultLanguage_2", stUsrData.enLanguage.ordinal());
                try {
                    getContentResolver().update(
                            Uri.parse("content://mstar.tv.usersetting/subtitlesetting"), vals,
                            null, null);
                }
                }
            }
            stSubtitleSet.fHardOfHearing = cursor.getInt(cursor.getColumnIndex("fHardOfHearing")) == 0 ? false
                    : true;
            stSubtitleSet.fEnableSubTitle = cursor.getInt(cursor.getColumnIndex("fEnableSubTitle")) == 0 ? false
                    : true;
        }
        return stSubtitleSet;
    }

        long ret = -1;
        EnumLanguage eLang1;
        EnumLanguage eLang2;
        ContentValues vals = new ContentValues();
        if (model.SubtitleDefaultLanguage == EnumLanguage.E_ENGLISH) {
            eLang1 = EnumLanguage.E_ENGLISH;
        }
            eLang1 = EnumLanguage.E_ACHINESE;
        }
            eLang1 = EnumLanguage.E_CHINESE;
        }
            eLang2 = EnumLanguage.E_ENGLISH;
        }
        }
            eLang2 = EnumLanguage.E_CHINESE;
        }
        vals.put("SubtitleDefaultLanguage_2", eLang2.ordinal());
        vals.put("fHardOfHearing", model.fHardOfHearing ? 1 : 0);
        vals.put("fEnableSubTitle", model.fEnableSubTitle ? 1 : 0);
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/subtitlesetting"), vals, null, null);
        }
            System.out.println("update tbl_SubtitleSetting ignored");
        }
            // TODO Auto-generated catch block
            e.printStackTrace();
    public MS_USER_LOCATION_SETTING queryUserLocSetting() {
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/userlocationsetting"), null, null, null,
                null);
        while (cursor.moveToNext()) {
            stUserLocationSetting.mLocationNo = (short) cursor.getInt(cursor
                    .getColumnIndex("u16LocationNo"));
            stUserLocationSetting.mManualLongitude = (short) cursor.getInt(cursor
                    .getColumnIndex("s16ManualLongitude"));
            stUserLocationSetting.mManualLatitude = (short) cursor.getInt(cursor
                    .getColumnIndex("s16ManualLatitude"));
        }
        cursor.close();
        return stUserLocationSetting;
    }
        ContentValues vals = new ContentValues();
        vals.put("u16LocationNo", model.mLocationNo);
        vals.put("s16ManualLongitude", model.mManualLongitude);
        vals.put("s16ManualLatitude", model.mManualLatitude);
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/userlocationsetting"), vals, null,
                    null);
        }
        catch (SQLException e) {
        }
        if (ret == -1) {
            System.out.println("update tbl_UserLocationSetting ignored");
        }
        try {
            TvManager.getDatabaseManager().setDatabaseDirtyByApplication(T_UserLocationSetting_IDX);
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     * 
        EnumLanguage eLang1;
        EnumLanguage eLang2;
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/soundsetting"), null, null, null, null);
        if (cursor.moveToFirst()) {
            soundpara.SoundMode = EN_SOUND_MODE.values()[cursor.getInt(cursor
                    .getColumnIndex("SoundMode"))];
            soundpara.AudysseyDynamicVolume = EN_AUDYSSEY_DYNAMIC_VOLUME_MODE.values()[cursor
                    .getInt(cursor.getColumnIndex("AudysseyDynamicVolume"))];
            soundpara.AudysseyEQ = EN_AUDYSSEY_EQ_MODE.values()[cursor.getInt(cursor
                    .getColumnIndex("AudysseyEQ"))];
            soundpara.SurroundSoundMode = EN_SURROUND_SYSTEM_TYPE.values()[cursor.getInt(cursor
                    .getColumnIndex("SurroundSoundMode"))];
            // soundpara.Surround =
            // EN_SURROUND_TYPE.values()[cursor.getInt(cursor.getColumnIndex("Surround"))];
            soundpara.SurroundMode = EN_SURROUND_MODE.values()[cursor.getInt(cursor
                    .getColumnIndex("Surround"))];
            soundpara.bEnableAVC = cursor.getInt(cursor.getColumnIndex("bEnableAVC")) == 0 ? false
                    : true;
            soundpara.Balance = (short) cursor.getInt(cursor.getColumnIndex("Balance"));
            soundpara.Primary_Flag = (short) cursor.getInt(cursor.getColumnIndex("Primary_Flag"));
            eLang1 = EnumLanguage.values()[cursor.getInt(cursor.getColumnIndex("enSoundAudioLan1"))];
            eLang2 = EnumLanguage.values()[cursor.getInt(cursor.getColumnIndex("enSoundAudioLan2"))];
            if (eLang1 == EnumLanguage.E_ENGLISH)
                soundpara.enSoundAudioLan1 = EnumLanguage.E_ENGLISH;
            else if (eLang1 == EnumLanguage.E_CHINESE)
                soundpara.enSoundAudioLan1 = EnumLanguage.E_CHINESE;
            else if (eLang1 == EnumLanguage.E_ACHINESE)
                soundpara.enSoundAudioLan1 = EnumLanguage.E_ACHINESE;
            else {
                ContentValues vals = new ContentValues();
                vals.put("enSoundAudioLan1", soundpara.enSoundAudioLan1.ordinal());
                try {
                            Uri.parse("content://mstar.tv.usersetting/soundsetting"), vals, null,
                            null);
                }
            else if (eLang2 == EnumLanguage.E_CHINESE)
                soundpara.enSoundAudioLan2 = EnumLanguage.E_CHINESE;
            else if (eLang2 == EnumLanguage.E_ACHINESE)
                soundpara.enSoundAudioLan2 = EnumLanguage.E_ACHINESE;
            else {
                vals.put("enSoundAudioLan2", soundpara.enSoundAudioLan2.ordinal());
                try {
                            Uri.parse("content://mstar.tv.usersetting/soundsetting"), vals, null,
                            null);
            soundpara.enSoundAudioChannel = EN_AUD_MODE.values()[cursor.getInt(cursor
                    .getColumnIndex("enSoundAudioChannel"))];
            soundpara.bEnableAD = cursor.getInt(cursor.getColumnIndex("bEnableAD")) == 0 ? false
                    : true;
            soundpara.ADOutput = EN_SOUND_AD_OUTPUT.values()[cursor.getInt(cursor
                    .getColumnIndex("ADOutput"))];
            soundpara.SPDIF_Delay = (short) cursor.getInt(cursor.getColumnIndex("SPDIF_Delay"));
            soundpara.Speaker_Delay = (short) cursor.getInt(cursor.getColumnIndex("Speaker_Delay"));
            // soundpara. = (short)
            // cursor.getInt(cursor.getColumnIndex("SpeakerPreScale"));
            // soundpara. = (short)
            // cursor.getInt(cursor.getColumnIndex("HeadPhonePreScale"));
            // soundpara. = (short)
            // cursor.getInt(cursor.getColumnIndex("SCART1PreScale"));
            // soundpara = (short)
            // soundpara. = (short)
            // cursor.getInt(cursor.getColumnIndex("bEnableHI"));
        }
    }

        EnumLanguage eLang1;
        EnumLanguage eLang2;
        ContentValues vals = new ContentValues();
        vals.put("SoundMode", model.SoundMode.ordinal());
        vals.put("AudysseyDynamicVolume", model.AudysseyDynamicVolume.ordinal());
        vals.put("AudysseyEQ", model.AudysseyEQ.ordinal());
        vals.put("SurroundSoundMode", model.SurroundSoundMode.ordinal());
        // vals.put("Surround", model.Surround.ordinal());
        vals.put("Surround", model.SurroundMode.ordinal());
        vals.put("bEnableAVC", model.bEnableAVC ? 1 : 0);
        vals.put("Volume", model.Volume);
        vals.put("HPVolume", model.HPVolume);
        vals.put("Balance", model.Balance);
        vals.put("Primary_Flag", model.Primary_Flag);
        if (model.enSoundAudioLan1 == EnumLanguage.E_ENGLISH) {
            eLang1 = EnumLanguage.E_ENGLISH;
        }
        }
        }
        }
        }
        }
        vals.put("MUTE_Flag", model.MUTE_Flag);
        vals.put("enSoundAudioChannel", model.enSoundAudioChannel.ordinal());
        vals.put("bEnableAD", model.bEnableAD ? 1 : 0);
        vals.put("ADVolume", model.ADVolume);
        vals.put("ADOutput", model.ADOutput.ordinal());
        vals.put("SPDIF_Delay", model.SPDIF_Delay);
        vals.put("Speaker_Delay", model.Speaker_Delay);
        // vals.put("spdifMode", model.spdifMode.ordinal());
        // vals.put("SpeakerPreScale", model.);
        // vals.put("HeadPhonePreScale", model.);
        // vals.put("SCART1PreScale", model.);
        // vals.put("SCART2PreScale", model.);
        // vals.put("bEnableHI", model.);
        try {
            ret = getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/soundsetting"), vals, null, null);
        }
        }
        }
        catch (TvCommonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

     * DB access control for SoundModeSeting astSoundModeSetting[] (tbl_SoundModeSetting)
     * 
     * @return
     */
    public SoundModeSeting[] querySoundModeSettings() {
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/soundmodesetting"), null, null, null,
                null);
        int i = 0;
        int length = astSoundModeSetting.length;
        while (cursor.moveToNext()) {
            if (i > length - 1) {
                break;
            }
            astSoundModeSetting[i].Bass = (short) cursor.getInt(cursor.getColumnIndex("Bass"));
            astSoundModeSetting[i].Treble = (short) cursor.getInt(cursor.getColumnIndex("Treble"));
            astSoundModeSetting[i].EqBand1 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand1"));
            astSoundModeSetting[i].EqBand2 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand2"));
            astSoundModeSetting[i].EqBand3 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand3"));
            astSoundModeSetting[i].EqBand4 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand4"));
            astSoundModeSetting[i].EqBand5 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand5"));
            astSoundModeSetting[i].EqBand6 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand6"));
            astSoundModeSetting[i].EqBand7 = (short) cursor
                    .getInt(cursor.getColumnIndex("EqBand7"));
            astSoundModeSetting[i].UserMode = cursor.getInt(cursor.getColumnIndex("UserMode")) == 0 ? false
                    : true;
            astSoundModeSetting[i].Balance = (short) cursor
                    .getInt(cursor.getColumnIndex("Balance"));
                    .getColumnIndex("enSoundAudioChannel"))];
            i++;
        }
    }

        ContentValues vals = new ContentValues();
        vals.put("Bass", model.Bass);
        vals.put("Treble", model.Treble);
        vals.put("EqBand1", model.EqBand1);
        vals.put("EqBand2", model.EqBand2);
        vals.put("EqBand3", model.EqBand3);
        vals.put("EqBand4", model.EqBand4);
        vals.put("EqBand5", model.EqBand5);
        vals.put("EqBand6", model.EqBand6);
        vals.put("EqBand7", model.EqBand7);
        vals.put("UserMode", model.UserMode ? 1 : 0);
        vals.put("Balance", model.Balance);
        vals.put("enSoundAudioChannel", model.enSoundAudioChannel.ordinal());
        try {
                    vals, null, null);
        }
        }
        }
            e.printStackTrace();
        }
        int id = 0;
        for (id = 0; id < 10; id++) {
            if ((short) queryPCModeIndex(id) == com.getVideoInfo().s16ModeIndex) {
                Log.i("DataBaseDeskImpl", "~~~~~~~~Hpos id is " + id + "~~~~~~~~~~~");
                break;
            }
        }
        Cursor cursor = getContentResolver().query(Uri.parse(str), null, null, null, null);
        int value = -1;
        if (cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex("u16UI_HorizontalStart"));
        }
    }

        for (id = 0; id < 10; id++) {
            if ((short) queryPCModeIndex(id) == com.getVideoInfo().s16ModeIndex) {
                Log.i("DataBaseDeskImpl", "~~~~~~~~Vpos id is " + id + "~~~~~~~~~~~");
                break;
            }
        Cursor cursor = getContentResolver().query(Uri.parse(str), null, null, null, null);
        int value = -1;
        if (cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex("u16UI_VorizontalStart"));
        }
    }

        int id = 0;
            if ((short) queryPCModeIndex(id) == com.getVideoInfo().s16ModeIndex) {
                Log.i("DataBaseDeskImpl", "~~~~~~~~clock id is " + id + "~~~~~~~~~~~");
                break;
        Cursor cursor = getContentResolver().query(Uri.parse(str), null, null, null, null);
        int value = -1;
        if (cursor.moveToFirst()) {
        }
        return value;
    }
        String str = "content://mstar.tv.usersetting/userpcmodesetting/" + String.valueOf(id);
        Cursor cursor = getContentResolver().query(Uri.parse(str), null, null, null, null);
        int value = -1;
        this.videopara = video;
    public MS_USER_LOCATION_SETTING getLocationSet() {
        return stUserLocationSetting;
    }
        int index;
        return this.astSoundModeSetting[index];
    }
        int index;
        mVifSet = stVif;
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://mstar.tv.usersetting/epgtimer/" + epgTimerIndex), null, null,
                null, null);
        cursor.moveToFirst();
        String eventName = new String();
        eventName = cursor.getString(cursor.getColumnIndex("sEventName"));
        System.out.println("\n=====>>eventName " + eventName + " @epgTimerIndex " + epgTimerIndex);
        cursor.close();
        try {
        }
            e.printStackTrace();