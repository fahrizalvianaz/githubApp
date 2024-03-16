package com.example.myapplication.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.databinding.FragmentFollowBinding
import com.example.myapplication.data.remote.response.ItemsItem
import com.example.myapplication.factory.FollowViewModelFactory
import com.example.myapplication.viewmodel.FollowViewModel


class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    private var position = 0
    private var username = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFollowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel : FollowViewModel by viewModels {
            FollowViewModelFactory(username)
        }
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager
        mainViewModel.follow.observe(viewLifecycleOwner) {follow -> setDataFollow(follow)}
        mainViewModel.isLoading.observe(requireActivity()) {showLoading(it)}
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME).toString()
        }
        if (position == 1) {
            mainViewModel.findUserFollowers(username)
        } else {
            mainViewModel.findUserFollowing(username)
        }
    }

    private fun setDataFollow(dataUserFollow : List<ItemsItem>) {
        val getUserData = dataUserFollow.map {
            ItemsItem(it.login, it.avatarUrl)
        }
        val adapter = UserAdapter(getUserData)
        binding.rvFollow.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}