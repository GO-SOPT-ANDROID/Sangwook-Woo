package org.android.go.sopt.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.Adapter.HeaderAdapter
import org.android.go.sopt.Adapter.ItemAdapter
import org.android.go.sopt.R
import org.android.go.sopt.data.ItemData
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
    get() = requireNotNull(_binding){"_binding이 null!"}

    private val mockItemList = listOf<ItemData>(
        ItemData(
            image = R.drawable.son,
            name = "손흥민",
            team = "토트넘 핫스퍼"
        ),
        ItemData(
            image = R.drawable.grealish,
            name = "잭 그릴리쉬",
            team = "맨체스터 시티"
        ),
        ItemData(
            image = R.drawable.mbappe,
            name = "킬리안 음바페",
            team = "파리 생제르망"
        ),
        ItemData(
            image = R.drawable.musiala,
            name = "자말 무시알라",
            team = "바이에른 뮌헨"
        ),
        ItemData(
            image = R.drawable.odegaard,
            name = "마르틴 외데고르",
            team = "아스널"
        ),
        ItemData(
            image = R.drawable.pedri,
            name = "페드리",
            team = "바르셀로나"
        ),
        ItemData(
            image = R.drawable.rashford,
            name = "마커스 래쉬포드",
            team = "맨체스터 유나이티드"
        ),
        ItemData(
            image = R.drawable.salah,
            name = "모하메드 살라",
            team = "리버풀"
        ),
        ItemData(
            image = R.drawable.theo,
            name = "테오 에르난데스",
            team = "AC밀란"
        ),
        ItemData(
            image = R.drawable.vinicius,
            name = "비니시우스 주니어",
            team = "레알 마드리드"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter1 = ItemAdapter(requireContext())
        val adapter2 = HeaderAdapter(requireContext())
        val adapter3 = ConcatAdapter(adapter2,adapter1)
        adapter1.setItemList(mockItemList)
        adapter2.setHeaderText("전세계 축구선수")
        binding.rvHome.adapter = adapter3

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object{
        fun newInstance(): HomeFragment {
           return HomeFragment()
        }
    }
}