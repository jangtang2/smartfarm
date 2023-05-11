package com.example.smartfarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentContainer
import com.example.smartfarm.databinding.FragmentMonitorBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

        return view
    }

}
