/*
 * Copyright (c) 2014 Xiao Bao Clark
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package com.actisec.clipcaster;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.actisec.clipcaster.parser.ClipParser;
import com.actisec.clipcaster.parser.Parsers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by xiao on 9/12/14.
 */
public class GlobalClipParser{
    public static final String CLIPLOG_FILENAME = "clip.log";
    public static final String CREDLOG_FILENAME = "creds.log";
    public static final List<String> mClips = new ArrayList<String>();

    public static void onClipEvent(final Context context, ClipboardManager manager){
        Log.d("ClipCaster", "" + System.currentTimeMillis());
        final ClipData primaryClip = getManager(context).getPrimaryClip();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < primaryClip.getItemCount(); i++){
            builder.append(primaryClip.getItemAt(i).coerceToText(context));
            if(i != primaryClip.getItemCount() - 1){
                builder.append('\n');
            }
        }
        final String text = builder.toString();
        mClips.add(text);
        for(ClipParser parser : Parsers.getClipParsers()){
            parser.onClip(context, new CredHandler(context), text);
        }
        onClipDebug(context,text);
    }


    private static void onClipDebug(Context context, final String text){
        if(Debug.isDebuggerConnected()) {
            System.out.println(text);
            writeToFile(context, CLIPLOG_FILENAME, text);
        }
    }

    private static void onCredDebug(Context context, final ScrapedCredentials credentials){
        if(Debug.isDebuggerConnected()) {
            System.out.println(credentials.toString());
            writeToFile(context, CREDLOG_FILENAME, credentials.toString());
        }
    }

    private static String currentTimestamp(){
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }


    private static void writeToFile(Context context, String name, String text){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new PrintWriter(context.openFileOutput(name, Context.MODE_APPEND)));
            writer.write(currentTimestamp() + ": " + text);
            writer.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class CredHandler implements com.actisec.clipcaster.CredHandler {

        private final Context mContext;
        private static final int NOTIF_ID = 0xface1345;

        private CredHandler(Context context) {
            mContext = context;
        }

        @Override
        public void handleCreds(ScrapedCredentials credentials) {
            postNotification(credentials);
            onCredDebug(mContext, credentials);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void postNotification(ScrapedCredentials credentials){

            Notification.Builder builder = new Notification.Builder(mContext);
            builder.setContentTitle(mContext.getString(R.string.creds_notif_title));
            builder.setSmallIcon(R.drawable.ic_launcher);

            Spanned contentText = getSpannedFromCreds(null, credentials, false);
            Spanned contentTextBig = getSpannedFromCreds(null, credentials, true);

            builder.setContentText(contentText);
            builder.setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext, ClipboardHistoryActivity.class), 0));
            builder.setTicker(contentText);

            builder.setStyle(new Notification.BigTextStyle().bigText(contentTextBig));
            Notification n = builder.build();
            ((NotificationManager)mContext.getSystemService(mContext.NOTIFICATION_SERVICE)).notify(NOTIF_ID, n);
        }

        private String getDefinitionHtml(String contents){
            return "<b>" + contents + ":</b> ";
        }

        private Spanned getSpannedFromCreds(Context context, ScrapedCredentials credentials, boolean splitLines){
            if(credentials.unknown != null){
                return Html.fromHtml(getDefinitionHtml(mContext.getString(R.string.cred)) + credentials.unknown);
            } else {
                assert(credentials.user != null || credentials.pass != null);
                String html = "";
                if (credentials.user != null){
                    html = getDefinitionHtml(mContext.getString(R.string.user)) + credentials.user;
                }

                if(credentials.pass != null){
                    if(credentials.user != null){
                        if(splitLines) {
                            html += "<br />";
                        } else {
                            html += ", ";
                        }
                    }
                    html += getDefinitionHtml(mContext.getString(R.string.pass)) + credentials.pass;
                }

                return Html.fromHtml(html);
            }
        }
    }

    private static ClipboardManager getManager(Context context){
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
}
