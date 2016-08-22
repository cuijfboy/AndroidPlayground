package name.ilab.playground.retrofit;

/**
 * Created by cuijfboy on 16/7/27.
 */
public class WeatherInfo {

    private Info weatherinfo;

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weatherinfo=" + weatherinfo +
                '}';
    }

    class Info {
        public String city;
        public String cityid;
        public String temp;
        public String WD;
        public String WS;
        public String SD;
        public String WSE;
        public String time;
        public String isRadar;
        public String Radar;
        public String njd;
        public String qy;
        public String rain;

        @Override
        public String toString() {
            return "Info{" +
                    "city='" + city + '\'' +
                    ", cityid='" + cityid + '\'' +
                    ", temp='" + temp + '\'' +
                    ", WD='" + WD + '\'' +
                    ", WS='" + WS + '\'' +
                    ", SD='" + SD + '\'' +
                    ", WSE='" + WSE + '\'' +
                    ", time='" + time + '\'' +
                    ", isRadar='" + isRadar + '\'' +
                    ", Radar='" + Radar + '\'' +
                    ", njd='" + njd + '\'' +
                    ", qy='" + qy + '\'' +
                    ", rain='" + rain + '\'' +
                    '}';
        }
    }

}
