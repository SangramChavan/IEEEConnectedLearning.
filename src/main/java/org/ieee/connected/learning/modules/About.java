package org.ieee.connected.learning.modules;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * Created by Sangram on 20/3/15.
 */
public class About extends FrameLayout {


        public About(Context context) {
            this(context, null);
        }

        public About(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public About(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup contain = (ViewGroup) inflater.inflate(org.ieee.connected.learning.R.layout.photo_scale_example, this, false);
            addView(contain);

        }
  }
