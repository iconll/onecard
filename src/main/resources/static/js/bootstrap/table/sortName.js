!function($) {
    'use strict';

    var BootstrapTable = $.fn.bootstrapTable.Constructor;
    BootstrapTable.prototype.onSort = function (event) {
        var $this = event.type === "keypress" ? $(event.currentTarget) : $(event.currentTarget).parent(),
            $this_ = this.$header.find('th').eq($this.index());

        this.$header.add(this.$header_).find('span.order').remove();

        if (this.options.sortName === $this.data('field')) {
            this.options.sortOrder = this.options.sortOrder === 'asc' ? 'desc' : 'asc';
        } else {
            //this.options.sortName = $this.data('field');
            //解决展示字段和排序字段不一致问题
            this.options.sortName = $this.data('sortName') ? $this.data('sortName') : $this.data('field');
            this.options.sortOrder = $this.data('order') === 'asc' ? 'desc' : 'asc';
        }
        this.trigger('sort', this.options.sortName, this.options.sortOrder);

        $this.add($this_).data('order', this.options.sortOrder);

        // Assign the correct sortable arrow
        this.getCaret();

        if (this.options.sidePagination === 'server') {
            this.initServer(this.options.silentSort);
            return;
        }

        this.initSort();
        this.initBody();
    };
    BootstrapTable.prototype.getCaret = function () {
        var that = this;

        $.each(this.$header.find('th'), function (i, th) {
            //$(th).find('.sortable').removeClass('desc asc').addClass($(th).data('field') === that.options.sortName === that.options.sortName ? that.options.sortOrder : 'both');
            //解决展示字段和排序字段不一致问题
            $(th).find('.sortable').removeClass('desc asc').addClass(($(th).data('field') === that.options.sortName || $(th).data('sortName') === that.options.sortName) ? that.options.sortOrder : 'both');
        });
    };
}(jQuery);