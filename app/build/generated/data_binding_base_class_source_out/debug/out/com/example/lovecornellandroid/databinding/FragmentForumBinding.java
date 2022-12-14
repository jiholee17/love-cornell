// Generated by view binder compiler. Do not edit!
package com.example.lovecornellandroid.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lovecornellandroid.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentForumBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final RecyclerView forumRecyclerview;

  @NonNull
  public final TextView headBg;

  @NonNull
  public final TextView headText;

  @NonNull
  public final TextView headText2;

  private FragmentForumBinding(@NonNull FrameLayout rootView,
      @NonNull ConstraintLayout constraintLayout, @NonNull RecyclerView forumRecyclerview,
      @NonNull TextView headBg, @NonNull TextView headText, @NonNull TextView headText2) {
    this.rootView = rootView;
    this.constraintLayout = constraintLayout;
    this.forumRecyclerview = forumRecyclerview;
    this.headBg = headBg;
    this.headText = headText;
    this.headText2 = headText2;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentForumBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentForumBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_forum, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentForumBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.constraint_layout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.forum_recyclerview;
      RecyclerView forumRecyclerview = ViewBindings.findChildViewById(rootView, id);
      if (forumRecyclerview == null) {
        break missingId;
      }

      id = R.id.head_bg;
      TextView headBg = ViewBindings.findChildViewById(rootView, id);
      if (headBg == null) {
        break missingId;
      }

      id = R.id.head_text;
      TextView headText = ViewBindings.findChildViewById(rootView, id);
      if (headText == null) {
        break missingId;
      }

      id = R.id.head_text2;
      TextView headText2 = ViewBindings.findChildViewById(rootView, id);
      if (headText2 == null) {
        break missingId;
      }

      return new FragmentForumBinding((FrameLayout) rootView, constraintLayout, forumRecyclerview,
          headBg, headText, headText2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
