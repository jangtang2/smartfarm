package com.example.smartfarm

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentContainer
import com.github.mikephil.charting.data.*
import com.example.smartfarm.databinding.FragmentMonitorBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.data.ChartData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

data class CommitData(val date: String, val commitNum: Int)

/**
 * A simple [Fragment] subclass.
 * Use the [MonitorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MonitorFragment : Fragment(R.layout.fragment_monitor) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMonitorBinding? = null
    private val binding get() = _binding!!

    val database = Firebase.database
    val myRef = database.getReference("avg_hour")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view : View = inflater.inflate(R.layout.fragment_monitor,container, false)

        val text_t: TextView = view.findViewById(R.id.txt2)
        val text_h: TextView = view.findViewById(R.id.txt3)

        val message_t : String? = this.arguments?.getString("temp")
        val message_h : String? = this.arguments?.getString("humi")

        text_t.text=message_t
        text_h.text=message_h

        setupLineChartData(view)

        return view
    }

    private fun setupLineChartData(view: View){

        val a1:Int=14
        val yVals = ArrayList<Entry>()
        yVals.add(Entry(1f, a1*1f, "1"))
        yVals.add(Entry(2f, 2f, "2"))
        yVals.add(Entry(3f, 4f, "3"))
        yVals.add(Entry( 4f, 7f, "4"))
        yVals.add(Entry(5f, 8f, "5"))
        yVals.add(Entry(6f, 10f, "6"))
        yVals.add(Entry(7f, 22f, "7"))
        yVals.add(Entry(8f, 12.5f, "8"))
        yVals.add(Entry(9f, 22f, "9"))
        yVals.add(Entry(10f, 32f, "10"))
        yVals.add(Entry(11f, 54f, "11"))
        yVals.add(Entry(12f, 28f, "12"))

        val set1: LineDataSet
        set1 = LineDataSet(yVals, "DataSet 1")

        set1.color = Color.BLUE
        set1.setCircleColor(Color.BLUE)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 0f
        set1.setDrawFilled(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        var lineChart = view.findViewById<LineChart>(R.id.TH_chart)
        // set data
        lineChart.setData(data)
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setPinchZoom(true)
        lineChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        lineChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        lineChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        //lineChart.setDrawGridBackground()
        lineChart.xAxis.labelCount = 11
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }

}
