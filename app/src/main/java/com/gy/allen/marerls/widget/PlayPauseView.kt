package com.gy.allen.marerls.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.gy.allen.marerls.R

open class PlayPauseView: View {

    lateinit var mPlayPauseListener: OnPlayPauseListener

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mPaint: Paint = Paint()
    private var mLeftPath: Path = Path()
    private var mRightPath: Path = Path()
    private var mGapWith: Float = 0.toFloat()
    private var mProgress: Float = 0.toFloat()

    private var mRect: Rect = Rect()
    private var isPlaying: Boolean = false
    private var mRectWidth: Float = 0.toFloat()  //圆内矩形宽度
    private var mRectHeight: Float = 0.toFloat() //圆内矩形高度
    private var mRectLT: Int = 0  //矩形左侧上侧坐标
    private var mRadius: Int = 0  //圆的半径
    private var mBgColor = Color.WHITE
    private var mBtnColor = Color.BLACK
    private var mDirection = Direction.POSITIVE.value
    private var mPadding: Float = 0.toFloat()
    private var mAnimDuration = 200//动画时间
    private var isEnable = true

    private lateinit var mContext: Context



    constructor(context: Context): super(context) {
        this.mContext = context
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        this.mContext = context
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        this.mContext = context
        mPaint.isAntiAlias = true
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayPauseView)
        mBgColor = typedArray.getColor(R.styleable.PlayPauseView_bg_color, Color.WHITE)
        mBtnColor = typedArray.getColor(R.styleable.PlayPauseView_btn_color, Color.BLACK)
        mGapWith = typedArray.getFloat(R.styleable.PlayPauseView_gap_width, 0.toFloat())
        mDirection = typedArray.getInt(R.styleable.PlayPauseView_anim_direction, Direction.POSITIVE.value)
        mPadding = typedArray.getFloat(R.styleable.PlayPauseView_space_padding, 0.toFloat())
        mAnimDuration = typedArray.getInt(R.styleable.PlayPauseView_anim_duration, 200)
        typedArray.recycle()
    }

    @SuppressWarnings("unused")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        when(widthMode) {
            MeasureSpec.EXACTLY -> {
                mHeight = Math.min(mWidth, mHeight)
                mWidth = mHeight
                setMeasuredDimension(mWidth, mHeight)
            }
            MeasureSpec.AT_MOST -> {
                val density: Float = resources.displayMetrics.density
                mHeight = (50 * density).toInt()
                mWidth = mHeight //默认50dp
                setMeasuredDimension(mWidth, mHeight)
            }
            else -> {}
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight= w
        initValue()
    }

    private fun initValue() {
        mRadius = mWidth / 2
        mPadding = if (mPadding == 0.toFloat()) mRadius / 3f else mPadding
        if (mPadding > mRadius / Math.sqrt(2.0) || mPadding < 0) {
            mPadding = mRadius / 3f //默认值
        }
        val space = mRadius / Math.sqrt(2.0) - mPadding
        mRectLT = (mRadius - space).toInt()
        val rectRB = (mRadius + space).toInt()

        mRect.top = mRectLT
        mRect.bottom = rectRB
        mRect.left = mRectLT
        mRect.right = rectRB

        mRectWidth = (space * 2 + 1f).toFloat()
        mRectHeight = (space * 2 + 1f).toFloat()

        mGapWith = if (mGapWith != 0.toFloat()) mGapWith else mRectWidth / 3
        mProgress = if (isPlaying) 0.toFloat() else 1.toFloat()

        mAnimDuration = if (mAnimDuration < 0) 200 else mAnimDuration
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        mLeftPath.rewind()
        mRightPath.rewind()

        val distance = mGapWith * (1 - mProgress) // 暂停时左右两边的距离
        val barWidth = mRectWidth / 2 - distance / 2  // 一个矩形的宽度
        val leftLeftTop = barWidth * mProgress // 左边矩形左上角

        val rightLeftTop = barWidth + distance  // 右边矩形左上角
        val rightRightTop = 2 * barWidth + distance // 右边矩形右上角
        val rightRightBottom = rightRightTop - barWidth * mProgress // 右边矩形右下角

        if (mDirection == Direction.NEGATIVE.value) {
            mLeftPath.moveTo(mRectLT.toFloat(), mRectLT.toFloat())
            mLeftPath.lineTo(leftLeftTop + mRectLT, mRectHeight + mRectLT)
            mLeftPath.lineTo(barWidth + mRectLT, mRectHeight + mRectLT)
            mLeftPath.lineTo(barWidth+mRectLT, mRectLT.toFloat())
            mLeftPath.close()

            mRightPath.moveTo(rightLeftTop + mRectLT, mRectLT.toFloat())
            mRightPath.lineTo(rightLeftTop + mRectLT, mRectHeight + mRectLT)
            mRightPath.lineTo(rightRightBottom + mRectLT, mRectHeight + mRectLT)
            mRightPath.lineTo(rightRightTop + mRectLT, mRectLT.toFloat())
            mRightPath.close()
        } else {
            mLeftPath.moveTo(leftLeftTop + mRectLT, mRectLT.toFloat())
            mLeftPath.lineTo(mRectLT.toFloat(), mRectHeight + mRectLT)
            mLeftPath.lineTo(barWidth + mRectLT, mRectHeight + mRectLT)
            mLeftPath.lineTo(barWidth + mRectLT, mRectLT.toFloat())
            mLeftPath.close()

            mRightPath.moveTo(rightLeftTop + mRectLT, mRectLT.toFloat())
            mRightPath.lineTo(rightLeftTop + mRectLT, mRectHeight + mRectLT)
            mRightPath.lineTo(rightLeftTop + mRectLT.toFloat() + barWidth, mRectHeight + mRectLT)
            mRightPath.lineTo(rightRightBottom + mRectLT, mRectLT.toFloat())
            mRightPath.close()
        }
        canvas.save()
        mPaint.color = mBgColor
        canvas.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), mRadius.toFloat(), mPaint)
        canvas.translate(mRectHeight / 8f * mProgress, 0.toFloat())
        val progress = if (isPlaying) 1 - mProgress else mProgress
        val corner = if (mDirection == Direction.NEGATIVE.value) -90 else 90
        val rotation = if (isPlaying) corner * (1 + progress) else progress * corner
        canvas.rotate(rotation, mWidth /2f, mHeight/ 2f)
        mPaint.color = mBtnColor
        mPaint.style = Paint.Style.FILL
        canvas.drawPath(mLeftPath, mPaint)
        canvas.drawPath(mRightPath, mPaint)
        canvas.restore()
    }

    private fun getPlayPauseAnim(): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(if (isPlaying) 1.toFloat() else 0.toFloat(), if (isPlaying) 0.toFloat() else 1.toFloat())
        valueAnimator.duration = mAnimDuration.toLong()
        valueAnimator.addUpdateListener { animation ->
            run {
                mProgress = animation.animatedValue as Float
                invalidate()
            }
        }
        return valueAnimator
    }

    open fun play() {
        if (getPlayPauseAnim() != null)
            getPlayPauseAnim().cancel()
        isPlaying = true
        getPlayPauseAnim().start()
    }

    open fun playWithoutAnim() {
        if (getPlayPauseAnim() != null) {
            getPlayPauseAnim().cancel()
        }
        isPlaying = true
        val valueAnimator = ValueAnimator.ofFloat(if (isPlaying) 1.toFloat() else 0.toFloat(), if (isPlaying) 0.toFloat() else 1.toFloat())
        valueAnimator.duration = 0
        valueAnimator.addUpdateListener { animation -> kotlin.run {
            mProgress = animation.animatedValue as Float
            invalidate()
        } }
        valueAnimator.start()
    }

    open fun pause() {
        if (getPlayPauseAnim() != null)
            getPlayPauseAnim().cancel()
        isPlaying = false
        getPlayPauseAnim().start()
    }

    open fun setOnPlayPauseListener() {
        setOnClickListener { _ ->
            if (!isEnable) {
                return@setOnClickListener
            }
            if (isPlaying) {
                pause()
                if (mPlayPauseListener != null) {
                    Toast.makeText(context, "暂停", Toast.LENGTH_LONG).show()
                    mPlayPauseListener.pause()
                }
            } else {
                play()
                if (mPlayPauseListener != null) {
                    Toast.makeText(context, "开始", Toast.LENGTH_LONG).show()
                    mPlayPauseListener.play()
                }
            }
        }
    }


    interface OnPlayPauseListener {
        fun play()
        fun pause()
    }




    enum class Direction private constructor(//逆时针
            internal var value: Int) {
        POSITIVE(1), //顺时针
        NEGATIVE(2)
    }

}