<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <!-- 引用子组件, 前面的list是分页组件暴露出来的一个回调方法, 后面的list是${domain}组件的list方法 -->
    <pagination ref="pagination" v-bind:list="list"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <#list fieldList as field>
          <th>${field.nameCn}</th>
        </#list>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>

      <tr v-for="${domain} in ${domain}s">
        <#list  fieldList as field>
          <td>{{${domain}.${field.nameHump}}}</td>
        </#list>
        <!-- 一行中的相关操作 -->
        <td>
          <div class="hidden-sm hidden-xs btn-group">

            <button v-on:click="edit(${domain})" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button v-on:click="del(${domain}.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul
                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>
      </tr>

      </tbody>
    </table>

    <!-- 模拟框 -->
    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">

              <#list fieldList as field>
                <div class="form-group">
                  <label class="col-sm-2 control-label">${field.nameCn}</label>
                  <div class="col-sm-10">
                    <input v-model="${domain}.${field.nameHump}" type="text"  class="form-control">
                  </div>
                </div>
              </#list>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

  </div>
</template>

<script>
    // 引入子组件
    import Pagination from "../../components/pagination";

    export default {
        name: "${domain}",
        components: {Pagination},
        // 使用data定义组件内的变量,可用于做双向数据绑定
        // ${domain}变量用来绑定form表单的数据
        data: function () {
            return {
                ${domain}: {},
                ${domain}s: []
            }
        },
        mounted: function () {
            let _this = this;
            _this.list(1);
            // sidebar激活函数一
            /*this.$parent.activeSidebar("${module}-${domain}-sidebar");*/
        },
        methods: {
            /*
            * 弹出新增模拟框
            * */
            add() {
                let _this = this;
                // 防止受上一次编辑记录的影响
                _this.${domain} = {};
                $("#form-modal").modal("show");
            },
            /*
             * 弹出编辑模拟框
             * */
            edit(${domain}) {
                let _this = this;
                // 点击编辑之后, 在文本框中显示${domain}
                // 保证对_this.${domain}变量的修改不会影响到 传递过来的${domain}变量
                _this.${domain} = $.extend({}, ${domain});
                $("#form-modal").modal("show");
            },
            /*
            *  展示${name}列表
            * */
            list(page) {
                let _this = this;
                // 加载框
                Loading.show();
                // post 默认用json来向后端传递数据
                // _this.$refs.xxx 获取子组件变量/操作
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/${module}/admin/${domain}/list', {
                    page: page,
                    size: _this.$refs.pagination.size,
                }).then((response) => {
                    Loading.hide();
                    console.log("查询到${name}结果:", response);
                    let res = response.data;
                    _this.${domain}s = res.content.list;
                    // 将数据渲染到子组件
                    _this.$refs.pagination.render(page, res.content.total);
                })
            },
            /*
            *  新增${name}数据
            * */
            save() {
                let _this = this;
                /*// 进行数据校验
                if (!Validator.require(_this.${domain}.name, "名称")
                    || !Validator.require(_this.${domain}.courseId, "课程ID")
                    || !Validator.require(_this.${domain}.courseId, "课程ID", 1, 8)){
                    return;
                }*/
                Loading.show();
                // post 默认用json来向后端传递数据
                _this.$ajax.post(process.env.VUE_APP_SERVER  + '/${module}/admin/${domain}/save', _this.${domain}
                ).then((response) => {
                    Loading.hide();
                    console.log("新增${name}结果:", response);
                    let res = response.data;
                    if(res.success){
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        toast.success("新增成功");
                    }else {
                        toast.error(res.message);
                    }

                })
            },
            /*
            *  删除${name}数据
            * */
            del(id) {
                let _this = this;
                // 确认弹出框
                Confirm.show("删除后不可恢复!", function () {
                    Loading.show();
                    // post 默认用json来向后端传递数据
                    // 使用Restful请求在后面直接拼接
                    _this.$ajax.delete(process.env.VUE_APP_SERVER  + '/${module}/admin/${domain}/delete/' + id
                    ).then((response) => {
                        Loading.hide();
                        console.log("删除${name}结果:", response);
                        let res = response.data;
                        if(res.success){
                            toast.success("删除成功!");
                            _this.list(1);
                        }else {
                            toast.error("删除失败!");
                        }
                    });
                });
            }
        }
    }
</script>