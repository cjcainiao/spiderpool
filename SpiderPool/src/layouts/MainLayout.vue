<!-- 主要布局页面 -->
<template>
    <div class="mina-layout">
        <el-container>
            <!-- 导航栏 -->
            <el-header>
                <GolbalHeader />
            </el-header>
            <!-- 显示区域 -->
            <el-main>
                <router-view />
            </el-main>

            <!-- 底部版权信息 -->
            <el-footer>
                Footer
            </el-footer>
        </el-container>
    </div>
</template>

<script setup lang="ts">
import GolbalHeader from '@/components/GolbalHeader.vue';

</script>

<style scoped></style>