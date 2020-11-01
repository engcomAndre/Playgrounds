package com.devmasterteam.photicker.views.utils;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.devmasterteam.photicker.R;
import com.devmasterteam.photicker.views.views.MainActivity;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SocialUtil {
    private static final String HASHTAG = "phototickerapp";

    public static void shareImageOnInsta(MainActivity mainActivity, RelativeLayout mRelativePhotoContent, View v) {
        PackageManager pkManager = mainActivity.getPackageManager();

        try {
            pkManager.getPackageInfo("com.instagram.android", 0);

            try {
                Bitmap image = ImageUtil.drawBitmap(mRelativePhotoContent);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp_file.jpg");
                try {
                    file.createNewFile();
                    FileOutputStream fo = new FileOutputStream(file);
                    fo.write(bytes.toByteArray());
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, Uri.parse("file:///sdcard/temp_file.jpg"));
                    sendIntent.setType("image/*");
                    sendIntent.setPackage("com.instagram.android");

                    v.getContext().startActivity(Intent.createChooser(sendIntent, mainActivity.getString(R.string.share_image)));

                } catch (FileNotFoundException e) {
                    Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_LONG).show();
            }

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(mainActivity, R.string.instagram_not_installed, Toast.LENGTH_LONG).show();
        }

    }

    public static void shareImageOnWhats(MainActivity mainActivity, RelativeLayout mRelativePhotoContent, View v) {
        PackageManager pkManager = mainActivity.getPackageManager();
        try {
            pkManager.getPackageInfo("com.whatsapp", 0);
            String fileName = "temp_file" + System.currentTimeMillis() + ".jpg";

            try {
                mRelativePhotoContent.setDrawingCacheEnabled(true);
                mRelativePhotoContent.buildDrawingCache(true);

                File imageFile = new File(Environment.getExternalStorageDirectory(), fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
                mRelativePhotoContent.getDrawingCache(true).compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();

                mRelativePhotoContent.setDrawingCacheEnabled(false);
                mRelativePhotoContent.destroyDrawingCache();

                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, HASHTAG);
                    sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/" + fileName));

                    sendIntent.setType("image/jpeg");
                    sendIntent.setPackage("com.whatsapp");

                    v.getContext().startActivity(Intent.createChooser(sendIntent, mainActivity.getString(R.string.share_image)));

                } catch (Exception e) {
                    Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
                }

            } catch (FileNotFoundException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_SHORT).show();
            }

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(mainActivity, R.string.whatsapp_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareImageOnTwitter(MainActivity mainActivity, RelativeLayout mRelativePhotoContent, View v) {
        PackageManager pkManager = mainActivity.getPackageManager();

        try {
            pkManager.getPackageInfo("com.twitter.android", 0);
            try {
                Intent twitterIntent = new Intent(Intent.ACTION_SEND);
                Bitmap image = ImageUtil.drawBitmap(mRelativePhotoContent);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp_file.jpg");
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());

                twitterIntent.putExtra(Intent.EXTRA_TEXT, HASHTAG);
                twitterIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file///sdcard/temp_file.jpg"));
                twitterIntent.setType("image/jpeg");

                PackageManager pm = mainActivity.getPackageManager();
                List<ResolveInfo> resolve = pm.queryIntentActivities(twitterIntent, PackageManager.MATCH_DEFAULT_ONLY);

                boolean resolved = false;
                for (ResolveInfo ri : resolve) {
                    if (ri.activityInfo.name.contains("twitter")) {
                        twitterIntent.setClassName(ri.activityInfo.packageName, ri.activityInfo.name);
                        resolved = true;
                        break;
                    }
                }
                v.getContext().startActivity(resolved ? twitterIntent : Intent.createChooser(twitterIntent, mainActivity.getString(R.string.share_image)));

            } catch (FileNotFoundException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(mainActivity, R.string.unexpected_error, Toast.LENGTH_LONG).show();
            }


        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(mainActivity, R.string.twitter_not_installed, Toast.LENGTH_LONG).show();
        }
    }

    public static void shareImageOnFace(MainActivity mainActivity, RelativeLayout mRelativePhotoContent, View v) {
        SharePhoto photo = new SharePhoto.Builder().setBitmap(ImageUtil.drawBitmap(mRelativePhotoContent)).build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .setShareHashtag(new ShareHashtag.Builder().setHashtag(HASHTAG).build())
                .build();

        new ShareDialog(mainActivity).show(content);
    }
}
