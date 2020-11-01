package com.devmasterteam.photicker.views.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.devmasterteam.photicker.R;
import com.devmasterteam.photicker.views.utils.ImageUtil;
import com.devmasterteam.photicker.views.utils.LongEventType;
import com.devmasterteam.photicker.views.utils.PermissionUtil;
import com.devmasterteam.photicker.views.utils.SocialUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    private static final int REQUEST_TAKE_PHOTO = 0;

    private final ViewHolder mViewHolder = new ViewHolder();
    private ImageView mImageSelected;
    private boolean mAutoIncrement;
    private LongEventType mLongEventType;
    private Handler mRepeatUpdateHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        List<Integer> mlistImages = ImageUtil.getImagesList();

        final LinearLayout content = findViewById(R.id.linear_horizontal_scroll_content);
        this.mViewHolder.mRelativePhotoContent = findViewById(R.id.relative_photo_content_draw);

        for (Integer imageId : mlistImages) {
            ImageView image = new ImageView(this);
            image.setImageBitmap(ImageUtil.decodeSampledBitmapFromResource(getResources(), imageId, 70, 70));
            image.setPadding(20, 10, 20, 10);

            BitmapFactory.Options dimensions = new BitmapFactory.Options();
            dimensions.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), imageId, dimensions);

            final int height = dimensions.outHeight;
            final int width = dimensions.outWidth;

            image.setOnClickListener(onClickImageOptions(this.mViewHolder.mRelativePhotoContent, imageId, width, height));

            content.addView(image);
        }

        this.mViewHolder.mImageInstagram = findViewById(R.id.image_instagram);
        this.mViewHolder.mImageFacebook = findViewById(R.id.image_facebook);
        this.mViewHolder.mImageTwitter = findViewById(R.id.image_twitter);
        this.mViewHolder.mImageWhatsApp = findViewById(R.id.image_whatsapp);

        this.mViewHolder.mLinearControlPanel = findViewById(R.id.linear_control_panel);
        this.mViewHolder.mLinearSharePanel = findViewById(R.id.linear_share_panel);

        this.mViewHolder.mButtonZoomIn = findViewById(R.id.image_zoom_in);
        this.mViewHolder.mButtonZoomOut = findViewById(R.id.image_zoom_out);
        this.mViewHolder.mButtonRotateLeft = findViewById(R.id.image_rotate_left);
        this.mViewHolder.mButtonRotateRight = findViewById(R.id.image_rotate_right);
        this.mViewHolder.mButtonRemove = findViewById(R.id.image_remove);
        this.mViewHolder.mButtonFinish = findViewById(R.id.image_finish);
        this.mViewHolder.mImagePhoto = findViewById(R.id.image_photo);


        this.setListeners();

    }

    private void setListeners() {

        this.findViewById(R.id.image_whatsapp).setOnClickListener(this);
        this.findViewById(R.id.image_twitter).setOnClickListener(this);
        this.findViewById(R.id.image_facebook).setOnClickListener(this);
        this.findViewById(R.id.image_instagram).setOnClickListener(this);

        this.findViewById(R.id.image_take_photo).setOnClickListener(this);
        this.findViewById(R.id.image_zoom_in).setOnClickListener(this);
        this.findViewById(R.id.image_zoom_out).setOnClickListener(this);
        this.findViewById(R.id.image_rotate_left).setOnClickListener(this);
        this.findViewById(R.id.image_rotate_right).setOnClickListener(this);
        this.findViewById(R.id.image_remove).setOnClickListener(this);
        this.findViewById(R.id.image_finish).setOnClickListener(this);

        this.findViewById(R.id.image_zoom_in).setOnTouchListener(this);
        this.findViewById(R.id.image_zoom_out).setOnTouchListener(this);
        this.findViewById(R.id.image_rotate_left).setOnTouchListener(this);
        this.findViewById(R.id.image_rotate_right).setOnTouchListener(this);

        this.findViewById(R.id.image_zoom_in).setOnLongClickListener(this);
        this.findViewById(R.id.image_zoom_out).setOnLongClickListener(this);
        this.findViewById(R.id.image_rotate_left).setOnLongClickListener(this);
        this.findViewById(R.id.image_rotate_right).setOnLongClickListener(this);

    }

    private View.OnClickListener onClickImageOptions(final RelativeLayout relativeLayout, final Integer imageId, int width, int height) {


        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageView image = new ImageView(MainActivity.this);
                image.setBackgroundResource(imageId);
                relativeLayout.addView(image);

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) image.getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

                mImageSelected = image;

                toggleControlPanel(true);

                image.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent motionEvent) {

                        int x, y;
                        switch (motionEvent.getAction()) {

                            case MotionEvent.ACTION_DOWN:
                                mImageSelected = image;
                                toggleControlPanel(true);
                                break;

                            case MotionEvent.ACTION_MOVE:
                                int coords[] = {0, 0};
                                relativeLayout.getLocationOnScreen(coords);

                                x = (int) (motionEvent.getRawX() - (image.getWidth() / 2));
                                y = (int) (motionEvent.getRawY() - ((coords[1] + 100) + (image.getHeight() / 2)));
                                image.setX(x);
                                image.setY(y);
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                        }
                        return true;
                    }
                });

            }
        };

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = ImageUtil.createImageFile(this);
                this.mViewHolder.mURIPhotoPath = Uri.fromFile(photoFile);
            } catch (IOException e) {
                Toast.makeText(this, "Não foi possível iniciar a câmera", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void toggleControlPanel(boolean showControls) {
        if (showControls) {
            this.mViewHolder.mLinearControlPanel.setVisibility(View.VISIBLE);
            this.mViewHolder.mLinearSharePanel.setVisibility(View.GONE);
        } else {
            this.mViewHolder.mLinearControlPanel.setVisibility(View.GONE);
            this.mViewHolder.mLinearSharePanel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_take_photo: {
                if (!PermissionUtil.hasCameraPermission(this)) {
                    PermissionUtil.asksCameraPermission(this);
                } else {
                    dispatchTakePictureIntent();
                }
                break;
            }
            case R.id.image_zoom_in:
                ImageUtil.handleZoomIn(this.mImageSelected);
                break;
            case R.id.image_zoom_out:
                ImageUtil.handleZoomOut(this.mImageSelected);
                break;
            case R.id.image_rotate_left:
                ImageUtil.handleRotateLeft(this.mImageSelected);
                break;
            case R.id.image_rotate_right:
                ImageUtil.handleRotateRight(this.mImageSelected);
                break;
            case R.id.image_finish:
                toggleControlPanel(false);
                break;
            case R.id.image_remove:
                this.mViewHolder.mRelativePhotoContent.removeView(this.mImageSelected);
                break;
            case R.id.image_instagram:
                SocialUtil.shareImageOnInsta(this, mViewHolder.mRelativePhotoContent, v);
                break;
            case R.id.image_facebook:
                SocialUtil.shareImageOnFace(this, mViewHolder.mRelativePhotoContent, v);
                break;
            case R.id.image_twitter:
                SocialUtil.shareImageOnTwitter(this, mViewHolder.mRelativePhotoContent, v);
                break;
            case R.id.image_whatsapp:
                SocialUtil.shareImageOnWhats(this, mViewHolder.mRelativePhotoContent, v);
                break;


            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            this.setResultPhotoAsBackGround();
        }

    }

    private void setResultPhotoAsBackGround() {
        int targetW = this.mViewHolder.mImagePhoto.getWidth();
        int targetH = this.mViewHolder.mImagePhoto.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.mViewHolder.mURIPhotoPath.getPath(), bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(this.mViewHolder.mURIPhotoPath.getPath(), bmOptions);

        Bitmap ratated = ImageUtil.rotateImageRequestId(bitmap, this.mViewHolder.mURIPhotoPath);

        this.mViewHolder.mImagePhoto.setImageBitmap(bitmap);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtil.CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage(getString(R.string.without_permission_camera_explanation))
                        .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();

            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.image_zoom_in) this.mLongEventType = LongEventType.ZoomIn;
        if (view.getId() == R.id.image_zoom_out) this.mLongEventType = LongEventType.ZoomOut;
        if (view.getId() == R.id.image_rotate_left) this.mLongEventType = LongEventType.RotateLeft;
        if (view.getId() == R.id.image_rotate_right)
            this.mLongEventType = LongEventType.RotateRight;
        mAutoIncrement = true;
        new RptUpadter().run();
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (id == R.id.image_zoom_in || id == R.id.image_zoom_out || id == R.id.image_rotate_right || id == R.id.image_rotate_left) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP && mAutoIncrement) {
                mAutoIncrement = false;
                this.mLongEventType = null;
            }
        }
        return false;
    }

    private static class ViewHolder {
        ImageView mImageInstagram;
        ImageView mImageFacebook;
        ImageView mImageTwitter;
        ImageView mImageWhatsApp;

        Uri mURIPhotoPath;
        ImageView mButtonZoomIn;
        ImageView mButtonZoomOut;
        ImageView mButtonRotateLeft;
        ImageView mButtonRotateRight;
        ImageView mButtonFinish;
        ImageView mButtonRemove;
        ImageView mImagePhoto;


        LinearLayout mLinearSharePanel;
        LinearLayout mLinearControlPanel;

        RelativeLayout mRelativePhotoContent;
    }


    private class RptUpadter implements Runnable {
        @Override
        public void run() {
            if (mAutoIncrement) {
                mRepeatUpdateHandler.postDelayed(new RptUpadter(), 50);
            }
            if (mLongEventType != null) {
                switch (mLongEventType) {
                    case ZoomIn:
                        ImageUtil.handleZoomIn(mImageSelected);
                        break;
                    case ZoomOut:
                        ImageUtil.handleZoomOut(mImageSelected);
                        break;
                    case RotateLeft:
                        ImageUtil.handleRotateLeft(mImageSelected);
                        break;
                    case RotateRight:
                        ImageUtil.handleRotateRight(mImageSelected);
                        break;
                }

            }

        }
    }
}
