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

        //temp line chart
        val at1 : Float? = this.arguments?.getString("atemp1")?.toFloat()
        val at2 : Float? = this.arguments?.getString("atemp2")?.toFloat()
        val at3 : Float? = this.arguments?.getString("atemp3")?.toFloat()
        val at4 : Float? = this.arguments?.getString("atemp4")?.toFloat()
        val at5 : Float? = this.arguments?.getString("atemp5")?.toFloat()
        val at6 : Float? = this.arguments?.getString("atemp6")?.toFloat()
        val at7 : Float? = this.arguments?.getString("atemp7")?.toFloat()
        val at8 : Float? = this.arguments?.getString("atemp8")?.toFloat()
        val at9 : Float? = this.arguments?.getString("atemp9")?.toFloat()
        val at10 : Float? = this.arguments?.getString("atemp10")?.toFloat()
        val at11 : Float? = this.arguments?.getString("atemp11")?.toFloat()
        val at12 : Float? = this.arguments?.getString("atemp12")?.toFloat()

        //val atp1: Float? = at1?.toFloat()

        val tmp_Vals = ArrayList<Entry>()
        if (at1 != null) {
            tmp_Vals.add(Entry(-24f, at1))
        }
        if (at2 != null) {
            tmp_Vals.add(Entry(-22f, at2))
        }
        if (at3 != null) {
            tmp_Vals.add(Entry(-20f, at3))
        }
        if (at4 != null) {
            tmp_Vals.add(Entry(-18f, at4))
        }
        if (at5 != null) {
            tmp_Vals.add(Entry(-16f, at5))
        }
        if (at6 != null) {
            tmp_Vals.add(Entry(-14f, at6))
        }
        if (at7 != null) {
            tmp_Vals.add(Entry(-12f, at7))
        }
        if (at8 != null) {
            tmp_Vals.add(Entry(-10f, at8))
        }
        if (at9 != null) {
            tmp_Vals.add(Entry(-8f, at9))
        }
        if (at10 != null) {
            tmp_Vals.add(Entry(-6f, at10))
        }
        if (at11 != null) {
            tmp_Vals.add(Entry(-4f, at11))
        }
        if (at12 != null) {
            tmp_Vals.add(Entry(-2f, at12))
        }

        val set1: LineDataSet
        set1 = LineDataSet(tmp_Vals, "DataSet 1")

        set1.color = Color.RED
        set1.setCircleColor(Color.RED)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 0f
        set1.setDrawFilled(false)

        val dataSets_1 = ArrayList<ILineDataSet>()
        dataSets_1.add(set1)
        val data1 = LineData(dataSets_1)

        var lineChart1 = view.findViewById<LineChart>(R.id.T_chart)
        // set data
        lineChart1.setData(data1)
        lineChart1.description.isEnabled = false
        lineChart1.legend.isEnabled = false
        lineChart1.setPinchZoom(true)
        lineChart1.xAxis.enableGridDashedLine(5f, 5f, 0f)
        lineChart1.axisRight.enableGridDashedLine(5f, 5f, 0f)
        lineChart1.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        lineChart1.xAxis.labelCount = 12
        lineChart1.xAxis.position = XAxis.XAxisPosition.BOTTOM


        //humi line chart
        val ah1 : Float? = this.arguments?.getString("ahumi1")?.toFloat()
        val ah2 : Float? = this.arguments?.getString("ahumi2")?.toFloat()
        val ah3 : Float? = this.arguments?.getString("ahumi3")?.toFloat()
        val ah4 : Float? = this.arguments?.getString("ahumi4")?.toFloat()
        val ah5 : Float? = this.arguments?.getString("ahumi5")?.toFloat()
        val ah6 : Float? = this.arguments?.getString("ahumi6")?.toFloat()
        val ah7 : Float? = this.arguments?.getString("ahumi7")?.toFloat()
        val ah8 : Float? = this.arguments?.getString("ahumi8")?.toFloat()
        val ah9 : Float? = this.arguments?.getString("ahumi9")?.toFloat()
        val ah10 : Float? = this.arguments?.getString("ahumi10")?.toFloat()
        val ah11 : Float? = this.arguments?.getString("ahumi11")?.toFloat()
        val ah12 : Float? = this.arguments?.getString("ahumi12")?.toFloat()

        //val atp1: Float? = at1?.toFloat()

        val hum_Vals = ArrayList<Entry>()
        if (ah1 != null) {
            hum_Vals.add(Entry(-24f, ah1))
        }
        if (ah2 != null) {
            hum_Vals.add(Entry(-22f, ah2))
        }
        if (ah3 != null) {
            hum_Vals.add(Entry(-20f, ah3))
        }
        if (ah4 != null) {
            hum_Vals.add(Entry(-18f, ah4))
        }
        if (ah5 != null) {
            hum_Vals.add(Entry(-16f, ah5))
        }
        if (ah6 != null) {
            hum_Vals.add(Entry(-14f, ah6))
        }
        if (ah7 != null) {
            hum_Vals.add(Entry(-12f, ah7))
        }
        if (ah8 != null) {
            hum_Vals.add(Entry(-10f, ah8))
        }
        if (ah9 != null) {
            hum_Vals.add(Entry(-8f, ah9))
        }
        if (ah10 != null) {
            hum_Vals.add(Entry(-6f, ah10))
        }
        if (ah11 != null) {
            hum_Vals.add(Entry(-4f, ah11))
        }
        if (ah12 != null) {
            hum_Vals.add(Entry(-2f, ah12))
        }

        val set2: LineDataSet
        set2 = LineDataSet(hum_Vals, "DataSet 2")

        set2.color = Color.BLUE
        set2.setCircleColor(Color.BLUE)
        set2.lineWidth = 1f
        set2.circleRadius = 3f
        set2.setDrawCircleHole(false)
        set2.valueTextSize = 0f
        set2.setDrawFilled(false)

        val dataSets2 = ArrayList<ILineDataSet>()
        dataSets2.add(set2)
        val data2 = LineData(dataSets2)

        var lineChart2 = view.findViewById<LineChart>(R.id.H_chart)
        // set data
        lineChart2.setData(data2)
        lineChart2.description.isEnabled = false
        lineChart2.legend.isEnabled = false
        lineChart2.setPinchZoom(true)

        lineChart2.xAxis.labelCount = 12
        lineChart2.xAxis.position = XAxis.XAxisPosition.BOTTOM

        return view
    }

}
