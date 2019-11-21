import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log

class MyAlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        //creating pop up that says Hydrate yourself
        val toast = Toast.makeText(context, "Hydrate yourself", Toast.LENGTH_LONG)
        toast.show()

    }
}