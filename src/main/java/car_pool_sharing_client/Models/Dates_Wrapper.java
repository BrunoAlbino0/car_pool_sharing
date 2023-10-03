package car_pool_sharing_client.Models;

public class Dates_Wrapper {

        private String pickup_date;
        private String drop_off_date;

        public Dates_Wrapper(String pickup_date, String drop_off_date) {
            this.pickup_date = pickup_date;
            this.drop_off_date = drop_off_date;
        }

        public Dates_Wrapper(){

        }

        public String getPickup_date() {
            return pickup_date;
        }

        public void setPickup_date(String pickup_date) {
            this.pickup_date = pickup_date;
        }

        public String getDrop_off_date() {
            return drop_off_date;
        }

        public void setDrop_off_date(String drop_off_date) {
            this.drop_off_date = drop_off_date;
        }
}
