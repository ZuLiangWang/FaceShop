package com.zuliangwang.FaceShop.bean;

import java.util.List;

/**
 * Created by zuliangwang on 15/10/29.
 */
public class FacePositionModel {
    /**
     * image_height : 360
     * face_shape : [{"left_eye":[{"y":180,"x":130},{"y":183,"x":138},{"y":185,"x":148},{"y":185,"x":157},{"y":184,"x":167},{"y":177,"x":160},{"y":172,"x":149},{"y":173,"x":138}],"face_profile":[{"y":178,"x":92},{"y":195,"x":93},{"y":213,"x":95},{"y":230,"x":97},{"y":246,"x":103},{"y":262,"x":111},{"y":278,"x":121},{"y":290,"x":134},{"y":302,"x":150},{"y":313,"x":165},{"y":316,"x":183},{"y":312,"x":200},{"y":304,"x":213},{"y":293,"x":225},{"y":282,"x":239},{"y":268,"x":250},{"y":254,"x":260},{"y":236,"x":266},{"y":218,"x":269},{"y":201,"x":271},{"y":182,"x":270}],"left_eyebrow":[{"y":160,"x":116},{"y":158,"x":129},{"y":158,"x":143},{"y":160,"x":157},{"y":159,"x":169},{"y":152,"x":157},{"y":149,"x":142},{"y":151,"x":128}],"right_eyebrow":[{"y":161,"x":256},{"y":158,"x":243},{"y":157,"x":233},{"y":159,"x":220},{"y":161,"x":208},{"y":152,"x":218},{"y":149,"x":232},{"y":151,"x":246}],"nose":[{"y":227,"x":186},{"y":186,"x":187},{"y":200,"x":178},{"y":213,"x":173},{"y":223,"x":164},{"y":236,"x":165},{"y":238,"x":175},{"y":243,"x":186},{"y":239,"x":199},{"y":235,"x":208},{"y":223,"x":208},{"y":214,"x":200},{"y":201,"x":196}],"right_eye":[{"y":179,"x":244},{"y":181,"x":235},{"y":184,"x":226},{"y":183,"x":218},{"y":185,"x":207},{"y":174,"x":214},{"y":172,"x":225},{"y":172,"x":234}],"mouth":[{"y":254,"x":146},{"y":269,"x":155},{"y":279,"x":169},{"y":283,"x":184},{"y":279,"x":201},{"y":267,"x":212},{"y":254,"x":222},{"y":249,"x":212},{"y":250,"x":200},{"y":251,"x":186},{"y":250,"x":173},{"y":251,"x":158},{"y":264,"x":157},{"y":271,"x":172},{"y":272,"x":186},{"y":270,"x":200},{"y":263,"x":211},{"y":253,"x":212},{"y":256,"x":199},{"y":256,"x":186},{"y":257,"x":174},{"y":256,"x":161}]}]
     * session_id :
     * errorcode : 0
     * errormsg : OK
     * image_width : 360
     */

    private int image_height;
    private String session_id;
    private int errorcode;
    private String errormsg;
    private int image_width;
    private List<FaceShapeEntity> face_shape;

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public void setImage_width(int image_width) {
        this.image_width = image_width;
    }

    public void setFace_shape(List<FaceShapeEntity> face_shape) {
        this.face_shape = face_shape;
    }

    public int getImage_height() {
        return image_height;
    }

    public String getSession_id() {
        return session_id;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public int getImage_width() {
        return image_width;
    }

    public List<FaceShapeEntity> getFace_shape() {
        return face_shape;
    }

    public static class FaceShapeEntity {
        /**
         * y : 180
         * x : 130
         */

        private List<LeftEyeEntity> left_eye;
        /**
         * y : 178
         * x : 92
         */

        private List<FaceProfileEntity> face_profile;
        /**
         * y : 160
         * x : 116
         */

        private List<LeftEyebrowEntity> left_eyebrow;
        /**
         * y : 161
         * x : 256
         */

        private List<RightEyebrowEntity> right_eyebrow;
        /**
         * y : 227
         * x : 186
         */

        private List<NoseEntity> nose;
        /**
         * y : 179
         * x : 244
         */

        private List<RightEyeEntity> right_eye;
        /**
         * y : 254
         * x : 146
         */

        private List<MouthEntity> mouth;

        public void setLeft_eye(List<LeftEyeEntity> left_eye) {
            this.left_eye = left_eye;
        }

        public void setFace_profile(List<FaceProfileEntity> face_profile) {
            this.face_profile = face_profile;
        }

        public void setLeft_eyebrow(List<LeftEyebrowEntity> left_eyebrow) {
            this.left_eyebrow = left_eyebrow;
        }

        public void setRight_eyebrow(List<RightEyebrowEntity> right_eyebrow) {
            this.right_eyebrow = right_eyebrow;
        }

        public void setNose(List<NoseEntity> nose) {
            this.nose = nose;
        }

        public void setRight_eye(List<RightEyeEntity> right_eye) {
            this.right_eye = right_eye;
        }

        public void setMouth(List<MouthEntity> mouth) {
            this.mouth = mouth;
        }

        public List<LeftEyeEntity> getLeft_eye() {
            return left_eye;
        }

        public List<FaceProfileEntity> getFace_profile() {
            return face_profile;
        }

        public List<LeftEyebrowEntity> getLeft_eyebrow() {
            return left_eyebrow;
        }

        public List<RightEyebrowEntity> getRight_eyebrow() {
            return right_eyebrow;
        }

        public List<NoseEntity> getNose() {
            return nose;
        }

        public List<RightEyeEntity> getRight_eye() {
            return right_eye;
        }

        public List<MouthEntity> getMouth() {
            return mouth;
        }

        public static class LeftEyeEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }

        public static class FaceProfileEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }

        public static class LeftEyebrowEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }

        public static class RightEyebrowEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }

        public static class NoseEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }

        public static class RightEyeEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }

        public static class MouthEntity {
            private int y;
            private int x;

            public void setY(int y) {
                this.y = y;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public int getX() {
                return x;
            }
        }
    }
}
