
 //https://stackoverflow.com/questions/20260416/retrieve-absolute-path-when-select-image-from-gallery-kitkat-android
 public void openChoosePhotoActivity() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

     @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	else if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String filePath = "";
                if (selectedImage.getScheme().equals("file"))
                    filePath = selectedImage.getPath();
                else {
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContext().getContentResolver().query(
                            selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    filePath = cursor.getString(columnIndex);
                    cursor.close();
                }

                Bitmap bm = BitmapFactory.decodeFile(filePath);
                if (bm == null || TextUtils.isEmpty(filePath)) {
                    showMessage(R.string.fiepath_not_found);
                    return;
                }
                File file = new File(filePath);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 50, out);
                    out.flush();
                    out.close();

                    userViewModel.changeAvatar(new File(filePath));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }