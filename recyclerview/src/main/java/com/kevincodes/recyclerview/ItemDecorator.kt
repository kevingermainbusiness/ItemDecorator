package com.kevincodes.recyclerview

import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextPaint
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple utility class that helps you customize your [RecyclerView]'s
 * [ItemTouchHelper.SimpleCallback.onChildDraw] behavior,
 * when [ItemTouchHelper.ACTION_STATE_SWIPE] is triggered.
 *
 * It lets you add a background, an icon, a text, all of which you can
 * customize to a certain extent, as you swipe from any side of the [ItemTouchHelper].
 * The [ItemDecorator] properties will be provided by the [ItemDecorator.Builder] class.
 * @author Kevin Germain
 */
data class ItemDecorator(
    var canvas: Canvas, var recyclerView: RecyclerView, var viewHolder: RecyclerView.ViewHolder,
    var dX: Float, var actionState: Int
) {
    @ColorInt
    private var mBgColorFromStartToEnd =
        ContextCompat.getColor(recyclerView.context, android.R.color.transparent)

    @ColorInt
    private var mBgColorFromEndToStart =
        ContextCompat.getColor(recyclerView.context, android.R.color.transparent)

    @ColorInt
    private var mIconTintFromStartToEnd: Int = Color.DKGRAY

    @ColorInt
    private var mIconTintFromEndToStart: Int = Color.DKGRAY

    @ColorInt
    private var mTextColorFromStartToEnd = Color.DKGRAY

    @ColorInt
    private var mTextColorFromEndToStart = Color.DKGRAY

    @DrawableRes
    private var mIconResIdFromStartToEnd = 0

    @DrawableRes
    private var mIconResIdFromEndToStart = 0

    private var mTextFromStartToEnd: String? = null
    private var mTextFromEndToStart: String? = null
    private var mTypefaceFromStartToEnd = Typeface.SANS_SERIF
    private var mTypefaceFromEndToStart = Typeface.SANS_SERIF

    private var mTextSizeFromStartToEnd = 14f
    private var mTextSizeFromEndToStart = 14f

    /**
     * @since 1.0.11
     * */
    private var mIconHorizontalMargin = 16f

    private var mIconMarginUnit = TypedValue.COMPLEX_UNIT_DIP
    private var mDefaultTextUnit = TypedValue.COMPLEX_UNIT_SP

    /* Default values */
    private var mCalculatedHorizontalMargin = TypedValue.applyDimension(
        mIconMarginUnit,
        mIconHorizontalMargin,
        recyclerView.context.resources.displayMetrics
    )

    /**
     * Should only be called from a [RecyclerView]'s [ItemTouchHelper.SimpleCallback.onChildDraw]
     * callback method.
     * Each property inside this Builder class will be provided by the [RecyclerView]'s
     * [ItemTouchHelper.SimpleCallback.onChildDraw] callback method,
     * which will make passing data to the [ItemDecorator.decorate] method possible.
     * *************************************************************************
     * @param canvas The canvas which RecyclerView is drawing its children on.
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     * @param viewHolder The ViewHolder which is being interacted by the User
     * or it was interacted and simply animating to its original position.
     * @param dX The amount of horizontal displacement caused by user's action.
     * @param actionState The type of interaction on the View,
     * It can either be an [ItemTouchHelper.ACTION_STATE_DRAG]
     * or [ItemTouchHelper.ACTION_STATE_SWIPE].
     * */
    class Builder(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        actionState: Int
    ) {
        private val mDecorator = ItemDecorator(canvas, recyclerView, viewHolder, dX, actionState)

        /**
         * Changes the default background color of both swiping directions
         * By default, the color for both swiping directions, is:
         * ContextCompat.getColor(recyclerView.context, android.R.color.transparent)
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultBgColor(@ColorInt color: Int): Builder {
            mDecorator.mBgColorFromStartToEnd = color
            mDecorator.mBgColorFromEndToStart = color
            return this
        }

        /**
         * Sets the icon shown on both swiping directions
         * @param resourceId The resource path to get the icon from
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultIcon(@DrawableRes resourceId: Int): Builder {
            mDecorator.mIconResIdFromStartToEnd = resourceId
            mDecorator.mIconResIdFromEndToStart = resourceId
            return this
        }

        /**
         * Assigns a default tint color to the icons shown in both swiping directions
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         * */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultIconTintColor(@ColorInt color: Int): Builder {
            mDecorator.mIconTintFromStartToEnd = color
            mDecorator.mIconTintFromEndToStart = color
            return this
        }

        /**
         * Adds a default text for both swiping directions
         * @param text default text for each side of the canvas
         * @return This instance of [Builder]
         * */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultText(text: String?): Builder {
            mDecorator.mTextFromStartToEnd = text
            mDecorator.mTextFromEndToStart = text
            return this
        }

        /**
         * Sets a default color for the texts in both swiping directions
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         * */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultTextColor(@ColorInt color: Int): Builder {
            mDecorator.mTextColorFromStartToEnd = color
            mDecorator.mTextColorFromEndToStart = color
            return this
        }

        /**
         * Sets the size of the text to be shown in both swiping directions
         *
         * By default, the text sizes are 14f, you can change that by calling this method,
         * and passing your size to the [size] parameter.
         * @param unit the unit to convert from, (e.g. [TypedValue.COMPLEX_UNIT_SP])
         * @param size the size to be set
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultTextSize(unit: Int = TypedValue.COMPLEX_UNIT_SP, size: Float): Builder {
            mDecorator.mDefaultTextUnit = unit
            mDecorator.mTextSizeFromStartToEnd = size
            mDecorator.mTextSizeFromEndToStart = size
            return this
        }

        /**
         * Sets the default typeface for the texts to be shown in both swiping directions
         *
         * [Typeface.SANS_SERIF] is set by default, you can change this
         * by passing your own Typeface to this method's [typeface] parameter
         * @param typeface, a Typeface )e.g. [Typeface.DEFAULT_BOLD]
         * @return This instance of [Builder]
         * @since 1.0.5
         * */
        @Deprecated("Use the Builder.set() method instead")
        fun setDefaultTypeFace(typeface: Typeface): Builder {
            mDecorator.mTypefaceFromStartToEnd = typeface
            mDecorator.mTypefaceFromEndToStart = typeface
            return this
        }

        /**
         * Sets the horizontal margin of the icons in the given unit (default is 16dp)
         * @param unit TypedValue, the unit to convert from, (e.g.[TypedValue.COMPLEX_UNIT_DIP])
         * @param iconHorizontalMargin the margin in the given unit
         * @return This instance of [Builder]
         */
        @Deprecated(message = "The margin should be specified in float")
        fun setIconHorizontalMargin(
            unit: Int = TypedValue.COMPLEX_UNIT_DIP,
            iconHorizontalMargin: Float
        ): Builder {
            mDecorator.mIconHorizontalMargin = iconHorizontalMargin
            return this
        }

        /**
         * Sets the background color shown while swiping from start to end
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndBgColor(@ColorInt color: Int): Builder {
            mDecorator.mBgColorFromStartToEnd = color
            return this
        }

        /**
         * Sets the background color shown while swiping from end to start
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartBgColor(@ColorInt color: Int): Builder {
            mDecorator.mBgColorFromEndToStart = color
            return this
        }

        /**
         * Defines the icon shown while swiping from start to end
         * @param drawableId The drawable path of the icon to be set
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndIcon(@DrawableRes drawableId: Int): Builder {
            mDecorator.mIconResIdFromStartToEnd = drawableId
            return this
        }

        /**
         * Defines the icon shown while swiping from end to start
         * @param drawableId The drawable path of the icon to be set
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartIcon(@DrawableRes drawableId: Int): Builder {
            mDecorator.mIconResIdFromEndToStart = drawableId
            return this
        }

        /**
         * Sets the tint color for the icon shown while swiping from start to end
         * @param tintColor a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndIconTint(@ColorInt tintColor: Int): Builder {
            mDecorator.mIconTintFromStartToEnd = tintColor
            return this
        }

        /**
         * Set the tint color for the icon shown while swiping from end to start
         * @param tintColor a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartIconTint(@ColorInt tintColor: Int): Builder {
            mDecorator.mIconTintFromEndToStart = tintColor
            return this
        }

        /**
         * Sets the text to be shown while swiping from start to end
         * @param text The string to be shown as text
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndText(text: String?): Builder {
            mDecorator.mTextFromStartToEnd = text
            return this
        }

        /**
         * Sets the text to be shown while swiping from end to start
         * @param text The string to be shown as text
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartText(text: String?): Builder {
            mDecorator.mTextFromEndToStart = text
            return this
        }

        /**
         * Sets the color of the text to be shown while swiping from start to end
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndTextColor(@ColorInt color: Int): Builder {
            mDecorator.mTextColorFromStartToEnd = color
            return this
        }

        /**
         * Set the color of the text to be shown while swiping from end to start
         * @param color a color in ARGB format (e.g. 0xFF0000FF for blue)
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartTextColor(@ColorInt color: Int): Builder {
            mDecorator.mTextColorFromEndToStart = color
            return this
        }

        /**
         * Sets the size of the text to be shown while swiping from start to end
         *
         * By default, the text size is 14f, you can change that by calling this method,
         * and passing your size to the [size] parameter.
         * @param unit the unit to convert from, (e.g. [TypedValue.COMPLEX_UNIT_SP])
         * @param size the size to be set
         * @since 1.0.7
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndTextSize(
            unit: Int = TypedValue.COMPLEX_UNIT_SP,
            size: Float
        ): Builder {
            mDecorator.mDefaultTextUnit = unit
            mDecorator.mTextSizeFromStartToEnd = size
            return this
        }

        /**
         * Sets the size of the text to be shown while swiping from end to start
         *
         * By default, the text size is 14f, you can change that by calling this method,
         * and passing your size to the [size] parameter.
         * @param unit the unit to convert from, (e.g. [TypedValue.COMPLEX_UNIT_SP])
         * @param size the size to be set
         * @since 1.0.7
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartTextSize(
            unit: Int = TypedValue.COMPLEX_UNIT_SP,
            size: Float
        ): Builder {
            mDecorator.mDefaultTextUnit = unit
            mDecorator.mTextSizeFromEndToStart = size
            return this
        }

        /**
         * Sets the Typeface of the text to be shown while swiping from start to end
         * @param typeface the Typeface to be set (e.g. [Typeface.SANS_SERIF])
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromStartToEndTypeface(typeface: Typeface): Builder {
            mDecorator.mTypefaceFromStartToEnd = typeface
            return this
        }

        /**
         * Sets the Typeface of the text to be shown while swiping from end to start
         * @param typeface the Typeface to be set (e.g. [Typeface.SANS_SERIF])
         * @return This instance of [Builder]
         */
        @Deprecated("Use the Builder.set() method instead")
        fun setFromEndToStartTypeface(typeface: Typeface): Builder {
            mDecorator.mTypefaceFromEndToStart = typeface
            return this
        }

        /**
         * Groups all previous set methods into one set method
         * @param bgColorFromStartToEnd The background color seen when you swipe from start to end
         * @param bgColorFromEndToStart The background color seen when you swipe from end to start
         * @param iconResIdFromStartToEnd The icon seen when you swipe from start to end
         * @param iconResIdFromEndToStart The icon seen when you swipe from end to start
         * @param iconTintColorFromStartToEnd The color of the [iconResIdFromStartToEnd]
         * @param iconTintColorFromEndToStart The color of the [iconResIdFromEndToStart]
         * @param textFromStartToEnd The text seen when you swipe from start to end
         * @param textFromEndToStart The text seen when you swipe from end to start
         * @param textColorFromStartToEnd The color of the [textFromStartToEnd]
         * @param textColorFromEndToStart The color of the [textFromEndToStart]
         * @param textSizeFromStartToEnd The text size of the [textFromStartToEnd]
         * @param textSizeFromEndToStart The text size of the [textFromEndToStart]
         * @param typeFaceFromStartToEnd The typeface of the [textFromStartToEnd]
         * @param typeFaceFromEndToStart The typeface of the [textFromEndToStart]
         * @param defaultIconMarginUnit The unit in which the icon margins need to be in
         * @param iconHorizontalMargin The margin of the [iconResIdFromStartToEnd]
         * and the [iconResIdFromEndToStart]
         * @since 1.0.11
         * */
        fun set(
            @ColorInt bgColorFromStartToEnd: Int = mDecorator.mBgColorFromStartToEnd,
            @ColorInt bgColorFromEndToStart: Int = mDecorator.mBgColorFromEndToStart,
            @DrawableRes iconResIdFromStartToEnd: Int = mDecorator.mIconResIdFromStartToEnd,
            @DrawableRes iconResIdFromEndToStart: Int = mDecorator.mIconResIdFromEndToStart,
            @ColorInt iconTintColorFromStartToEnd: Int = mDecorator.mIconTintFromStartToEnd,
            @ColorInt iconTintColorFromEndToStart: Int = mDecorator.mIconTintFromEndToStart,
            textFromStartToEnd: String,
            textFromEndToStart: String,
            @ColorInt textColorFromStartToEnd: Int = mDecorator.mTextColorFromStartToEnd,
            @ColorInt textColorFromEndToStart: Int = mDecorator.mTextColorFromEndToStart,
            textSizeFromStartToEnd: Float = mDecorator.mTextSizeFromStartToEnd,
            textSizeFromEndToStart: Float = mDecorator.mTextSizeFromEndToStart,
            typeFaceFromStartToEnd: Typeface = mDecorator.mTypefaceFromStartToEnd,
            typeFaceFromEndToStart: Typeface = mDecorator.mTypefaceFromEndToStart,
            defaultTextUnit: Int = TypedValue.COMPLEX_UNIT_SP,
            defaultIconMarginUnit: Int = mDecorator.mIconMarginUnit,
            iconHorizontalMargin: Float = mDecorator.mIconHorizontalMargin
        ): Builder {
            mDecorator.mBgColorFromStartToEnd = bgColorFromStartToEnd
            mDecorator.mBgColorFromEndToStart = bgColorFromEndToStart
            mDecorator.mIconResIdFromStartToEnd = iconResIdFromStartToEnd
            mDecorator.mIconResIdFromEndToStart = iconResIdFromEndToStart
            mDecorator.mIconTintFromStartToEnd = iconTintColorFromStartToEnd
            mDecorator.mIconTintFromEndToStart = iconTintColorFromEndToStart
            mDecorator.mTextFromStartToEnd = textFromStartToEnd
            mDecorator.mTextFromEndToStart = textFromEndToStart
            mDecorator.mDefaultTextUnit = defaultTextUnit
            mDecorator.mTextSizeFromStartToEnd = textSizeFromStartToEnd
            mDecorator.mTextSizeFromEndToStart = textSizeFromEndToStart
            mDecorator.mTextColorFromStartToEnd = textColorFromStartToEnd
            mDecorator.mTextColorFromEndToStart = textColorFromEndToStart
            mDecorator.mTypefaceFromStartToEnd = typeFaceFromStartToEnd
            mDecorator.mTypefaceFromEndToStart = typeFaceFromEndToStart
            mDecorator.mIconMarginUnit = defaultIconMarginUnit
            mDecorator.mIconHorizontalMargin = iconHorizontalMargin
            create().decorate()
            return this
        }

        /**
         * @return The referenced [ItemDecorator] [mDecorator] in this [Builder] class
         */
        fun create(): ItemDecorator = mDecorator
    }

    /**
     * Assigns a tint color to an icon drawable
     * @param tintColor the tint color that will be assigned to the [Drawable]
     * */
    private fun Drawable.colorFilter(@ColorInt tintColor: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            this.setColorFilter(tintColor, PorterDuff.Mode.MULTIPLY)
        } else {
            this.colorFilter = BlendModeColorFilter(tintColor, BlendMode.SRC_ATOP)
        }
    }

    /**
     * Decorates an [RecyclerView.ViewHolder.itemView] as [ItemTouchHelper.LEFT]
     * or [ItemTouchHelper.RIGHT] are being fired,
     * with the dedicated backgrounds, texts and icons.
     *
     *  It does that by using the exact [recyclerView], [viewHolder], [dX] and [canvas]
     *  to draw on. These values are provided by its parent class [ItemDecorator], which itself
     *  gets the exact values from the [ItemDecorator.Builder] class.
     *  */
    fun decorate() {
        if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) return
        if (dX > 0) fromStartToEndBehavior() else fromEndToStartBehavior()
    }

    /**
     * if [dX] > 0, that means that the user is swiping from the start of the
     * screen to the end of the screen (left to right).
     *
     * The [canvas] takes the size of the [viewHolder].
     * */
    private fun fromStartToEndBehavior() {
        canvas.clipRect(
            viewHolder.itemView.left,
            viewHolder.itemView.top,
            viewHolder.itemView.left + dX.toInt(),
            viewHolder.itemView.bottom
        )
        // Draws a color drawable on the canvas, with the same size as the canvas
        if (mBgColorFromStartToEnd != 0) {
            val cvBackgroundColor = ColorDrawable(mBgColorFromStartToEnd)
            cvBackgroundColor.bounds = canvas.clipBounds
            cvBackgroundColor.draw(canvas)
        }
        // Draws the icon contextualizing the swipe action
        var iconSize = 0
        if (mIconResIdFromStartToEnd != 0 && dX > mCalculatedHorizontalMargin) {
            val icon = ContextCompat.getDrawable(
                recyclerView.context, mIconResIdFromStartToEnd
            )
            icon?.let {
                iconSize = it.intrinsicHeight
                val halfIcon = iconSize / 2
                val top =
                    viewHolder.itemView.top +
                            ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                it.setBounds(
                    (viewHolder.itemView.left + mCalculatedHorizontalMargin).toInt(),
                    top,
                    (viewHolder.itemView.left + mCalculatedHorizontalMargin + it.intrinsicWidth).toInt(),
                    top + iconSize
                )
                mIconTintFromStartToEnd?.let { iconTintColor ->
                    it.colorFilter(iconTintColor)
                }
                it.draw(canvas)
            }
        }
        // Draws the descriptive text contextualizing the swipe action
        mTextFromStartToEnd?.let {
            if (dX > mCalculatedHorizontalMargin + iconSize) {
                val textPaint = TextPaint()
                textPaint.isAntiAlias = true
                textPaint.textSize = TypedValue.applyDimension(
                    mDefaultTextUnit,
                    mTextSizeFromStartToEnd,
                    recyclerView.context.resources.displayMetrics
                )
                textPaint.color = mTextColorFromStartToEnd
                textPaint.typeface = mTypefaceFromStartToEnd
                val textTop =
                    (viewHolder.itemView.top + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0 + textPaint.textSize / 2).toInt()
                canvas.drawText(
                    it,
                    viewHolder.itemView.left + mCalculatedHorizontalMargin + iconSize + (if (iconSize > 0) mCalculatedHorizontalMargin / 2 else 0).toFloat(),
                    textTop.toFloat(),
                    textPaint
                )
            }
        }
    }

    /**
     * if [dX] < 0, that means that the user is swiping from the end of the
     * screen to the start of the screen (right to left).
     *
     * The [canvas] takes the size of the [viewHolder].
     * */
    private fun fromEndToStartBehavior() {
        canvas.clipRect(
            viewHolder.itemView.right + dX.toInt(),
            viewHolder.itemView.top,
            viewHolder.itemView.right,
            viewHolder.itemView.bottom
        )
        // Draws a color drawable on the canvas, with the same size as the canvas
        if (mBgColorFromEndToStart != 0) {
            val cvBackgroundColor = ColorDrawable(mBgColorFromEndToStart)
            cvBackgroundColor.bounds = canvas.clipBounds
            cvBackgroundColor.draw(canvas)
        }
        // Draws the icon contextualizing the swipe action
        var imgEnd = viewHolder.itemView.right
        var iconSize = 0
        if (mIconResIdFromEndToStart != 0 && dX < -mCalculatedHorizontalMargin) {
            val icon = ContextCompat.getDrawable(
                recyclerView.context, mIconResIdFromEndToStart
            )
            icon?.let {
                iconSize = it.intrinsicHeight
                val halfIcon = iconSize / 2
                val top =
                    viewHolder.itemView.top +
                            ((viewHolder.itemView.bottom - viewHolder.itemView.top) / 2 - halfIcon)
                imgEnd =
                    (viewHolder.itemView.right - mCalculatedHorizontalMargin - halfIcon * 2).toInt()
                it.setBounds(
                    imgEnd,
                    top,
                    (viewHolder.itemView.right - mCalculatedHorizontalMargin).toInt(),
                    top + it.intrinsicHeight
                )
                mIconTintFromEndToStart?.let { iconTintColor ->
                    it.colorFilter(iconTintColor)
                }
                it.draw(canvas)
            }
        }
        // Draws the descriptive text contextualizing the swipe action
        mTextFromEndToStart?.let {
            if (dX < -mCalculatedHorizontalMargin - iconSize) {
                val textPaint = TextPaint()
                textPaint.isAntiAlias = true
                textPaint.textSize = TypedValue.applyDimension(
                    mDefaultTextUnit,
                    mTextSizeFromEndToStart,
                    recyclerView.context.resources.displayMetrics
                )
                textPaint.color = mTextColorFromEndToStart
                textPaint.typeface = mTypefaceFromEndToStart
                val width = textPaint.measureText(it)
                val textTop =
                    (viewHolder.itemView.top + (viewHolder.itemView.bottom - viewHolder.itemView.top) / 2.0 + textPaint.textSize / 2.0).toInt()

                canvas.drawText(
                    it,
                    imgEnd - width - if (imgEnd == viewHolder.itemView.right) mCalculatedHorizontalMargin else mCalculatedHorizontalMargin / 2,
                    textTop.toFloat(),
                    textPaint
                )
            }
        }
    }
}